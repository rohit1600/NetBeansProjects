/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* 
*CommentList.java Interface for a list of Comments
**/
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

// CommentList.java Interface for a list of Comments

public interface CommentList extends Remote {
  	
        Vector newComment(CommentObject g) throws RemoteException;  
	    
        Vector allComments()throws RemoteException;

        int getVersion() throws RemoteException;

}
