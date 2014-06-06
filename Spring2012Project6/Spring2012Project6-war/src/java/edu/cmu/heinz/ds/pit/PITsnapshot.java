package edu.cmu.heinz.ds.pit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PITsnapshot", urlPatterns = {"/PITsnapshot"})
public class PITsnapshot extends HttpServlet {

    int numPlayers = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head></head><body>");

        try {
            // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Connection con = cf.createConnection();
            con.start(); // don't forget to start the connection
            QueueSession session = (QueueSession) con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // The PITsnapshot Queue is used for responses from the Players to this serverlet
            Queue q = (Queue) ctx.lookup("jms/PITsnapshot");
            MessageConsumer reader = session.createConsumer(q);

            /*
             * Throw out old PITsnapshot messages that may have been left from past
             * snapshots that did not complete (because of some error).
             */
            ObjectMessage m = null;
            while ((m = (ObjectMessage) reader.receiveNoWait()) != null) {
                System.out.println("Found an orphaned PITsnapshot message");
            }

            // Initialize the snapshot my sending a marker to a Player
            sendInitSnapshot();

            /*
             * Receive the snapshot messages from all Players.
             * Each snapshot is a HahsMap.  Put them into an array of HashMaps             * 
             */
            HashMap state[] = new HashMap[numPlayers + 1];
            int stateResponses = 0;
            int failures = 0;
            while (stateResponses < numPlayers) {  
                if ((m = (ObjectMessage) reader.receive(1000)) == null) {
                    if (++failures > 10) {
                        System.out.println("Not all players reported, giving up after "+stateResponses);
                        break;
                    }
                    continue;
                }
                state[stateResponses++] = (HashMap) m.getObject();
                }


            /*
             * For each commodity, sum the number of them reported from
             * each Player.  Store these into a two dimensional table
             * that will then be used to generate the report back to the user.
             */
            String commodity[] = {"rice", "gold", "oil"};
            int total[][] = new int[numPlayers][commodity.length];
            for (int c = 0; c < commodity.length; c++) {
                for (int p = 0; p < stateResponses; p++) {
                    try {
                        Integer ccount = (Integer) state[p].get(commodity[c]);
                        if (ccount == null) {
                            total[p][c] = 0;
                        } else {
                            total[p][c] = (Integer) ccount.intValue();
                        }
                    } catch (Exception e) {
                        System.out.println("Servlet threw exception " + e);
                    }
                }
            }

            /*
             * Now turn the table of commodities, and the state from each Player
             * into a response to the user.
             */
            for (int c = 0; c < commodity.length; c++) {
                int ctotal = 0;
                out.print("<h2>Commodity: " + commodity[c] + "</h2>");
                out.print("<table border='1'><tr><th>Player</th><th>Quantity</th></tr>");
                for (int p = 0; p < stateResponses; p++) {
                    out.print("<tr><td>" + p + "</td><td>" + total[p][c] + "</td></tr>");
                    ctotal += total[p][c];
                }
                out.print("<tr><td><b>Total</b></td><td>" + ctotal + "</td></tr>");
                out.print("</table></br></br>");
            }

            // Close the connection
            con.close();

            out.println("</BODY></HTML>");

        } catch (Exception e) {
            System.out.println("Servlet threw exception " + e);
        } finally {
            out.println("</body></html>");
            out.close();
        }
    }

    /*
     * Initiate the snapshot by sending a Marker message to one of the Players (Player0)
     * Any Player could have been used to initiate the snapshot.
     */
    private void sendInitSnapshot() {
        try {
            // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Queue q = (Queue) ctx.lookup("jms/PITplayer0");
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer writer = session.createProducer(q);
            
            /*
             * As part of the snapshot algorithm, players need to record 
             * what other Players they receive markers from.
             * "-1" indicates to the PITplayer0 that this marker is coming from
             * the monitor, not another Player.
             */
            Marker m = new Marker(-1);
            ObjectMessage msg = session.createObjectMessage(m);
            System.out.println("Initiating Snapshot");
            writer.send(msg);
            con.close();
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
}
