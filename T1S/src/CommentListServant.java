/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentList servant holds a CommentList object and handles the version.
* 
**/
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

public class CommentListServant implements CommentList {

    private Vector theList;
    private int version;

    /**
     * Constructor for the CommentListServant
     * @throws RemoteException 
     */
    public CommentListServant() throws RemoteException {
        theList = new Vector();
        version = 0;
    }

    /**
     * method to add new comment to the list
     * method adds the CommentObject to the list and returns the cumalative list
     * @param co
     * @return
     * @throws RemoteException 
     */
    public Vector newComment(CommentObject co) throws RemoteException {
        version++;
        Comment s = new CommentServant(co, version);
        theList.addElement(s);
        return theList;
    }

    /**
     * return the entire list of comments
     * @return
     * @throws RemoteException 
     */
    public Vector allComments() throws RemoteException {
        return theList;
    }

    /**
     * returns the version of the CommentList object.
     * @return
     * @throws RemoteException 
     */
    @Override
    public int getVersion() throws RemoteException {
        return version;
    }

}