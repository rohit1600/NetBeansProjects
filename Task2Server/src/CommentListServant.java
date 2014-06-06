/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentListServant holds a CommentList object and handles the version.
* 
**/
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

public class CommentListServant implements CommentList {

    private ArrayList subscriberList=new ArrayList();
    private Vector theList;
    private int version;
    /**
     * Constructor for CommentListServant
     * @throws RemoteException 
     */
    public CommentListServant() throws RemoteException {
        theList = new Vector();
        version = 0;
    }
    /**
     * newComment() method adds CommentObject to Vector
     * Calls the allComments to publish the comments to all clients
     * @param co
     * @return
     * @throws RemoteException 
     */
    public Comment newComment(CommentObject co) throws RemoteException {
        version++;
        Comment s = new CommentServant(co, version);
        theList.addElement(s);
        this.notifyClients();
        return s;
    }

    /**
     * to display all comments to registered clients
     * calls notify method on all clients
     * @return
     * @throws RemoteException 
     */
    @Override
    public Vector notifyClients() throws RemoteException {
        for (int i=0;i<subscriberList.size();i++){
            //get the last added element
            CommentServant cs=(CommentServant) theList.lastElement();
            
            ((Notifiable) subscriberList.get(i)).notify(cs.getAllState());
        }
        return theList;
    }
    /**
     * returns the version of the CommentListServant
     * @return
     * @throws RemoteException 
     */
    @Override
    public int getVersion() throws RemoteException {
        return version;
    }

    /**
     * registers the client by adding to a Vector
     * subscriber list contains the list of all the reader clients
     * @param n
     * @throws RemoteException 
     */
    @Override
    public void registerCallBack(Notifiable n) throws RemoteException {
        subscriberList.add(n);
    }
}