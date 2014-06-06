/**
* Author: Rohit Rajagopal
* Last Modified: Mar 9, 2012
*
* This skeleton is used to communicate with server and client
* This class implements the socket level programming in the server
* It is instantiated with a object which implements the Binder interface
* 
*/
package edu.cmu.andrew.rohitraj;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Binder_Skeleton {

    //instance variable
    Binder myServer;

    /**
     * Constructor takes Person object as argument
     * starts server which returns the parameters of myServer on requests
     * @param s 
     */
    public Binder_Skeleton(Binder s) {
        myServer = s;
    }
    
    /**
     * method accepts requests from client and server
     * differentiates server and client based on object received
     */
    public void serve() {
        try {
            //server listens to port 9090
            ServerSocket s = new ServerSocket(9090);
            while (true) {
                Socket socket = s.accept();
                //Stream to receive client objects
                ObjectInputStream i = new ObjectInputStream(socket.getInputStream());
                Object method = i.readObject();
                
                //server sends RemoteObjectReference object first 
                //client sends String object first
                //checking object type will reveal if request is from server/client
                if (method instanceof RemoteObjectReference) {
                    
                    //Additionally server sends String object to bind
                    myServer.bind((String) i.readObject(), (RemoteObjectReference)method);
                    socket.close();
                    
                } else if (method instanceof String) {
                    //stream to send objects to client
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    //lookup string in treemap of myServer and send to client
                    o.writeObject(myServer.lookup((String) method));
                    o.flush();
                    socket.close();
                }
            }
        } catch (Exception t) {
            System.out.println("Error " + t);
            System.exit(0);
        }
    }
}