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
    RemoteObjectReference ror=null;
    Socket socket;
    ObjectOutputStream o;
    ObjectInputStream i;
    //count is used as requestID to verify for correct response
    //static int count=0;

    public Person_Stub(RemoteObjectReference r) throws Exception {
        this.ror=r;
    }

    /**
     * method to invoke remote method to get ID
     */
    public int getID() throws Exception {
        //client communicates on port 9000
        socket = new Socket(new String(ror.getInetAddress()), ror.getPort());
        RequestReplyMessage rrm=new RequestReplyMessage();
        //setting the remote reference object
        rrm.setObjectReference(ror); 
        //Setting message type to 0 implies request
        //rrm.setMessageType(0); 
        rrm.setMethodID(0);
        o = new ObjectOutputStream(socket.getOutputStream());
        o.writeObject(rrm);
        o.flush();
        //Stream to receive the response from server
        i = new ObjectInputStream(socket.getInputStream());
        RequestReplyMessage ret = (RequestReplyMessage) i.readObject();
        socket.close();
        String s=new String(ret.getArguments());
        return Integer.parseInt(s);
    }

    /**
     * method to invoke remote method to get Name
     */
    public String getName() throws Exception {
        //client communicates on port 9000
        socket = new Socket("localhost", ror.getPort());
        RequestReplyMessage rrm=new RequestReplyMessage();
        //setting the remote reference object
        rrm.setObjectReference(ror); 
        //Setting message type to 1 implies request
        rrm.setMethodID(1);
        //Stream to receive the response from server
        o = new ObjectOutputStream(socket.getOutputStream());
        //String "name" used as identifier for remote method
        o.writeObject(rrm);
        o.flush();
        i = new ObjectInputStream(socket.getInputStream());
        RequestReplyMessage ret = (RequestReplyMessage) i.readObject();
        socket.close();
        String s=new String(ret.getArguments());
        return s;
    }
}