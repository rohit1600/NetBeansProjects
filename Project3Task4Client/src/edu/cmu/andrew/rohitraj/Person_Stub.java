/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* person_Stub class implements the Person interface
* An object of this class acts as a communication stub for the client
*/
package edu.cmu.andrew.rohitraj;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Person_Stub implements Person {

    //instance variables
    Socket socket;
    ObjectOutputStream o;
    ObjectInputStream i;

    public Person_Stub() throws Exception {
    }

    /**
     * method to invoke remote method to get ID
     */
    public int getID() throws Exception {
        //client communicates on port 9000
        socket = new Socket("localhost", 9000);
        o = new ObjectOutputStream(socket.getOutputStream());
        //String "ID" is sent as an identifier for the remote method to be invoked
        o.writeObject("ID");
        o.flush();
        i = new ObjectInputStream(socket.getInputStream());
        int ret = i.readInt();
        socket.close();
        return ret;
    }

    /**
     * method to invoke remote method to get Name
     */
    public String getName() throws Exception {
        //client communicates on port 9000
        socket = new Socket("localhost", 9000);
        o = new ObjectOutputStream(socket.getOutputStream());
        //String "name" used as identifier for remote method
        o.writeObject("name");
        o.flush();
        i = new ObjectInputStream(socket.getInputStream());
        String ret = (String) (i.readObject());
        socket.close();
        return (String) ret;
    }
}