
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jms.*;
import javax.naming.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "FetchResponses", urlPatterns = {"/FetchResponses"})
public class FetchResponses extends HttpServlet {

    // Lookup the ConnectionFactory using resource injection and assign to cf
    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory cf;
    // lookup the Queue using resource injection and assign to q
    @Resource(lookup = "jms/myQueueThree")
    private Queue q3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //String val = request.getParameter("simpleTextMessage");
        try {
            // With the ConnectionFactory, establish a Connection, and then a Session on that Connection
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * You send and receive messages to/from the queue via a session. We
             * want to send, making us a MessageProducer Therefore create a
             * MessageProducer for the session
             */
            MessageConsumer reader = session.createConsumer(q3);
            con.start();

            /*
             * The message can be text, a byte stream, a Java object, or a
             * attribute/value Map We want to send a text message. BTW, a text
             * message can be a string, or it can be an XML object, and often a
             * SOAP object.
             */
            
            TextMessage msg = (TextMessage) reader.receive(1000);
            String message = msg.getText();
            out.println("<HTML><BODY>");
            while (!message.isEmpty()) {
                out.println("<H1>Read " + message + " to queue</H1>");
                msg = (TextMessage) reader.receive(1000);
                message=msg.getText();
            }

            // Close the connection
            con.close();
            out.println("</BODY></HTML>");
            //System.out.println("Servlet sent " + reader.receive(1000) + " to queue");
        } catch (Exception e) {
            System.out.println("Servlet threw exception " + e);
        } finally {
            out.close();
        }
    }
}