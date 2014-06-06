/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* 
* Notifiable.java Interface for a ReaderClient
**/

import java.rmi.Remote;
import java.rmi.RemoteException;

/* Notifiable.java implementors may be notified */
public interface Notifiable extends Remote {
    public void notify(CommentObject c) throws RemoteException;
}