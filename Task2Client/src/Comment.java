/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* 
*Comment.java Interface for a Comment
**/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Comment extends Remote {

   int getVersion() throws RemoteException;

   CommentObject getAllState() throws RemoteException;

}