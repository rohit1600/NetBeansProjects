/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* Contains the main method for running the chat client
* sends comments entered by the user and receives a cumulative list of comments
* from server and displays on the console.
**/
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommentListClient {

    /**
     * main method looks up the registry for the CommentList object
     * then uses the new Comment method of the remote object to write comments
     * The server returns the list of comments and the client displays on console
     * 
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception {
       
        //buffered reader to read the input from the user
        BufferedReader in =
                new BufferedReader(
                new InputStreamReader(System.in));
        // connect to the rmiregistry and get a remote reference to the CommentList
        // object.
        CommentList c = (CommentList) Naming.lookup("//localhost/CoolChatRoom");
        System.out.println("Found ChatBox. Enter ! to quit");
        while (true) {
            try {
                // prompt the user 
                System.out.println("<client>");
                // get a line
                String line = in.readLine();
                // if a "!" is entered just exit
                if (line.equals("!")) {
                    System.exit(0);
                }
                // if it's not a return call the newComment method on the 
                // CommentList object to write comments. 
                if (!line.equals("")) {
                    //The commentlist returns a Vector of CommentObjects
                    Vector v=c.newComment(new CommentObject(line));
                    for (int i=0;i<v.size();i++){
                        Comment com=(Comment) v.get(i);
                        //display on console
                        System.out.println(com.getAllState());
                    }
                    
                }
            } catch (RemoteException e) {
                System.out.println("allComments: " + e.getMessage());
            }
        }
    }
}