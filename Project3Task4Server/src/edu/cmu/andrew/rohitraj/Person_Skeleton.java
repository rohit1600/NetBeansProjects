/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class implements the socket level programming in the server
* It is instantiated with a object which implements the Person interface
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
     * Constructor takes Person object as argument
     * starts server which returns the parameters of myServer on requests
     * @param s 
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
                String method = (String) i.readObject();
                if (method.equals("ID")) {
                    int a = myServer.getID();
                    //stream to send objects to client
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    o.writeInt(a);
                    o.flush();
                } else if (method.equals("name")) {
                    String n = myServer.getName();
                    //stream to send objects to client
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    o.writeObject(n);
                    o.flush();
                }
            }
        } catch (Exception t) {
            System.out.println("Error " + t);
            System.exit(0);
        }
    }
}