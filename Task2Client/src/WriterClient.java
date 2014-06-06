
/**
 * Author: Rohit Rajagopal Last Modified: April 6, 2012
 * 
 * Contains the main method for running the chat client writes comments entered
 * by the user
 *
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Vector;

public class WriterClient {

    public static void main(String args[]) throws Exception {
        BufferedReader in =
                new BufferedReader(
                new InputStreamReader(System.in));
        String screenName = null;
        while (screenName == null) {
            System.out.println("Enter Screen Name : ");
            screenName = in.readLine();
        }
        CommentList c = (CommentList) Naming.lookup("//localhost/CoolChatRoom");
        System.out.println("Found ChatRoom. Enter ! to quit");
        while (true) {
            try {
                // prompt the user 
                System.out.print("<client>");
                // get a line
                String line = in.readLine();
                // if a "!" is entered just exit
                CommentObject co = new CommentObject(screenName, line);
                if (line.equals("!")) {
                    System.exit(0);
                }
                //else write the comment object to server
                if (!line.equals("")) {
                    c.newComment(co);
                }
            } catch (RemoteException e) {
                System.out.println("allComments: " + e.getMessage());
            }
        }
    }
}