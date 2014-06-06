package edu.cmu.heinz.ds.pit;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jms.*;
import javax.naming.*;

@WebServlet(name = "PITinit", urlPatterns = {"/PITinit"})
public class PITinit extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        resetAllPlayers(3);
        sendInit(0, 3, "rice");
        sendInit(1, 3, "oil");
        sendInit(2, 3, "gold");

        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PITinitServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>PIT has been initiated</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void sendInit(int playerNumber, int numPlayers, String commodity) {

        try {
             // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Queue q = (Queue) ctx.lookup("jms/PITplayer" + playerNumber);
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer writer = session.createProducer(q);
            
            // Create a new hand to send to the Player
            NewHand hand = new NewHand();
            hand.numPlayers = numPlayers;
            for (int i = 0; i < 9; i++) {
                hand.handCard.add(commodity); // Give each player 9 of the same commodity
            }
            
            // Send the hand to the Player
            ObjectMessage msg = session.createObjectMessage(hand);
            String date = DateFormat.getTimeInstance().format(new Date());
            System.out.println(date + ": sending newhand to " + playerNumber);
            writer.send(msg);
            con.close();
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }

    private void resetAllPlayers(int numPlayers) {

        try {
            // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * For each player, send a Reset message, and wait if its reply.
             * We need to wait for a reply, for the NewHands cannot be distributed
             * until every Player is in a reset state.
             */
            for (int player = 0; player < numPlayers; player++) {
                System.out.println(DateFormat.getTimeInstance().format(new Date()) + "Reset of PITplayer" + player);
                Queue q = (Queue) ctx.lookup("jms/PITplayer" + player);
                MessageProducer writer = session.createProducer(q);

                /*
                 * A Reset is an object passed back and forth to initiate and 
                 * acknowledge an reset operation
                 */
                Reset reset = new Reset();
                ObjectMessage resetMessage = session.createObjectMessage(reset);

                writer.send(resetMessage);
              
                // Read the PITmonitor Queue for the Reset acknowledgement
                Queue rq = (Queue) ctx.lookup("jms/PITmonitor");
                MessageConsumer reader = session.createConsumer(rq);

                // Always remember to start a connection when receiving from it!
                con.start();
                
                // Give a very long wait.  It should not take that long, but fail if it does not come back by then
                ObjectMessage m = (ObjectMessage) reader.receive(10000);


                if (m == null) {
                    System.out.println("ERROR:  Receive of reset acknowledgement time out from PITplayer" + player);
                    throw new Throwable("ERROR:  Receive of reset acknowledgement time out from PITplayer" + player);
                }
                if (!(((Reset) m.getObject()) instanceof Reset)) {
                    System.out.println("ERROR:  Bad reset acknowledgement back from PITplayer" + player);
                    throw new Throwable("ERROR:  Bad reset acknowledgement back from PITplayer" + player);
                }                System.out.println(DateFormat.getTimeInstance().format(new Date()) + "Reset of PITplayer" + player + " ACKNOWLEDGED");
            }
            session.close();
            con.close();
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
}
