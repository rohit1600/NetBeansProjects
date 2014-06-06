/**
 * Author: Rohit Rajagopal Last Modified: April 6, 2012
 * 
* Contains the main method for running the chat client which reads comments 
* entered by any user
*
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ReaderClient extends UnicastRemoteObject implements Notifiable {

    /**
     * Constructor for ReaderClient
     *
     * @throws RemoteException
     */
    public ReaderClient() throws RemoteException {
    }

    /**
     * method to print out the commentObject to console
     *
     * @param c
     * @throws RemoteException
     */
    public void notify(CommentObject c) throws RemoteException {
        System.out.println(c);
    }

    /**
     * main method to write the CommentObject 
     * creates a new ReaderClient which implements Notifiable
     * looksup the CommentListServant object in registry
     * sends the Notifiable object to server
     * @param args
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception {
        ReaderClient rc = new ReaderClient();
        BufferedReader in =
                new BufferedReader(
                new InputStreamReader(System.in));
        CommentList c = (CommentList) Naming.lookup("//localhost/CoolChatRoom");
        c.registerCallBack(rc);

    }
}