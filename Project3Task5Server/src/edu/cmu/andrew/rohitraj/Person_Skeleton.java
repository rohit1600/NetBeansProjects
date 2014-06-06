/**
* Author: Rohit Rajagopal
* Last Modified: Mar 9, 2012
*
* This class implements the socket level programming in the server
* It is instantiated with a object which implements the Person interface
* takes client requests on port 9000
* sends and receives RequestReplyMessage objects
*/
package edu.cmu.andrew.rohitraj;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Person_Skeleton {

    //instance variable
    Person myServer;

    /**
     * Constructor takes object which implements the Person interface
     * starts server which returns the parameters of myServer on client requests
     */
    public Person_Skeleton(Person s) {
        myServer = s;
    }

    public void serve() {
        try {
            //server listens to port 9000
            ServerSocket s = new ServerSocket(9000);
            while (true) {
                Socket socket = s.accept();
                //Stream to receive client objects
                ObjectInputStream i = new ObjectInputStream(socket.getInputStream());
                RequestReplyMessage method = (RequestReplyMessage) i.readObject();
                
                //if methodID=0,  then return ID
                //if methodID=1, then return name
                if (method.getMethodID()==0) {
                    //get the ID for methodID=0
                    RequestReplyMessage response=method;
                    int id=myServer.getID();
                    //messageType set to 1 to indicate response
                    response.setMessageType(1);
                    //return statement sent in arguments attribute
                    response.setArguments((((Integer) id).toString()).getBytes());
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    o.writeObject(response);
                    o.flush();
                } else if (method.getMethodID()==1) {
                    //new object instantiated to hold same information as request
                    RequestReplyMessage response=method;
                    String res=myServer.getName();
                    //return value are sent in the arguments field
                    response.setArguments(res.getBytes());
                    //stream to send response
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    o.writeObject(response);
                    o.flush();
                }
            }
        } catch (Exception t) {
            System.out.println("Error " + t);
            System.exit(0);
        }
    }
}