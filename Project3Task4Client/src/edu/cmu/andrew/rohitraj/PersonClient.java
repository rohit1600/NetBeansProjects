/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class contains the main() to implement the client side to demonstrate 
* RMI. 
*/
package edu.cmu.andrew.rohitraj;

public class PersonClient {

    /**
     * main method of client side
     * no command line arguments
     * @param args 
     */
    public static void main(String args[]) {
        try {
            //create a new client stub for communication with server
            //Person_Stub implements Person interface
            Person p = new Person_Stub();
            //call getID() on Person_Stub object
            int id = p.getID();
            System.out.println("ID = " + id);
            //call getName() on Person_Stub to get the name
            String name = p.getName();
            System.out.println(name + " has ID number: " + id);
        } catch (Exception t) {
            t.printStackTrace();
            System.exit(0);
        }
    }
}