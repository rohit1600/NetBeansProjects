/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentListServer contains the main method for the server
* main method creates a CommentListServant object and binds it to registry with
* a name.
* 
**/
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class CommentListServer {
	public static void main(String args[]){
         //System.setSecurityManager(new RMISecurityManager());
        try{
            CommentList aCommentlist = new CommentListServant();
            CommentList stub = (CommentList) UnicastRemoteObject.exportObject(aCommentlist,0);
			Naming.rebind("CoolChatRoom", aCommentlist); 
            System.out.println("CommentList server ready");
        }catch(Exception e) {
            System.out.println("CommentList server main " + e.getMessage());
        }
    }
}