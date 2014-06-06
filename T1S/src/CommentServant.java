/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentServant handles a CommentObject and its version.
* 
* 
**/

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class CommentServant extends UnicastRemoteObject implements Comment {

    int myVersion;
    CommentObject co;

    /**
     * Constructor for CommentServant object
     * @param g
     * @param version
     * @throws RemoteException 
     */
    public CommentServant(CommentObject g, int version) throws RemoteException {
        co = g;
        myVersion = version;
    }

    /**
     * returns the version of the CommentObject
     * @return
     * @throws RemoteException 
     */
    public int getVersion() throws RemoteException {
        return myVersion;
    }

    /**
     * returns the CommentObject
     * @return
     * @throws RemoteException 
     */
    public CommentObject getAllState() throws RemoteException {
        return co;
    }
}