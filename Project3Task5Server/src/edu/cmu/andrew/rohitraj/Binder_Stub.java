/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* Binder_Stub class implements the Binder interface
* An object of this class acts as a communication stub to communicate with the 
* registry on port 9090
*/
package edu.cmu.andrew.rohitraj;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Rohit
 */
public class Binder_Stub implements Binder{
    
    //instance variables
    Socket socket;
    ObjectOutputStream o;
    ObjectInputStream i;
    
    /**
     * method sets up connection with registry and looks up a string argument
     * returns a remoteObjectReference object
     */
    public RemoteObjectReference lookup(String s) throws Exception{
        socket = new Socket("localhost", 9090);
        o = new ObjectOutputStream(socket.getOutputStream());
        o.writeObject(s);
        o.flush();
        i = new ObjectInputStream(socket.getInputStream());
        RemoteObjectReference ret = (RemoteObjectReference) i.readObject();
        socket.close();
        return ret;
    }
    
    /**
     * method to bind remoteObjectReference to the registry
     */
    public void bind(String s, RemoteObjectReference ror) throws Exception{
        socket = new Socket("localhost", 9090);
        o = new ObjectOutputStream(socket.getOutputStream());
        //the RemoteObjectReference object is sent first
        o.writeObject(ror);
        o.flush();
        //next the String is sent
        o.writeObject(s);
        o.flush();
        socket.close();
    }
    
}
