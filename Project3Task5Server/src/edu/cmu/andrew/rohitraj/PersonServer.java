/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class creates a Person_Servant object and a RemoteObjectReference. It uses
* the Binder_Stub to make a bind call on the registry. It creates a Person_Skeleton
* object and asks it to serve.
* 
*/
package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonServer {
    
    /**
     * main() method
     */
    public static void main(String []args){
        try {
            Person p=new Person_Servant("Mike",23);
            RemoteObjectReference ror= new RemoteObjectReference();
            InetAddress i=InetAddress.getLocalHost();
            //set the IP address with getAddress() method of InetAddress class
            ror.setInetAddress(("127.0.0.1").getBytes());
            ror.setPort(9000);
            
            //Binding the RemoteObjectReference to the registry
            Binder_Stub bs=new Binder_Stub();
            bs.bind("Mike", ror);
            
            //Starting the server and responding to client requests
            Person_Skeleton ps=new Person_Skeleton(p);
            ps.serve();
        } catch (Exception ex) {
            Logger.getLogger(PersonServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
