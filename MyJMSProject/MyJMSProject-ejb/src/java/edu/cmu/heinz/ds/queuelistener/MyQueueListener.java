package edu.cmu.heinz.ds.queuelistener;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jms.*;
import javax.naming.*;
import javax.servlet.annotation.WebServlet;

// This creates the mapping of this MessageListener to the apropriate Queue
@MessageDriven(mappedName = "jms/myQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MyQueueListener implements MessageListener {
    
    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory cf;
    
    @Resource(lookup = "jms/myQueueTwo")
    private Queue q2;

    public MyQueueListener() {
    }

    /*
     * When a message is available in jms/myQueueOne, then onMessage is called.
     */
    public void onMessage(Message message) {
        try {
            String tmt = "";
            // Since there can be different types of Messages, make sure this is the right type.
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                tmt = tm.getText();
                tmt = tmt + " was processed by MyQueueListener";
                System.out.println("MyQueueListener received: " + tmt);
                // With the ConnectionFactory, establish a Connection, and then a Session on that Connection
                Connection con = cf.createConnection();
                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

                /*
                 * You send and receive messages to/from the queue via a
                 * session. We want to send, making us a MessageProducer
                 * Therefore create a MessageProducer for the session
                 */
                MessageProducer writer = session.createProducer(q2);

                /*
                 * The message can be text, a byte stream, a Java object, or a
                 * attribute/value Map We want to send a text message. BTW, a
                 * text message can be a string, or it can be an XML object, and
                 * often a SOAP object.
                 */
                TextMessage msg = session.createTextMessage();
                msg.setText(tmt);

                // Send the message to the destination Queue
                writer.send(msg);

                // Close the connection
                con.close();
            } else {
                System.out.println("I don't handle messages of this type");
            }
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
}