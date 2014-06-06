/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class contains the main() to implement the server side to demonstrate 
* RMI. 
*/
package edu.cmu.andrew.rohitraj;

public class PersonServer {

    public static void main(String args[]) {
        //create Person_Servant object with name="Mike" and ID=23
        Person p = new Person_Servant("Mike", 23);
        //Create Person_Skeleton object to handle client requests
        Person_Skeleton ps = new Person_Skeleton(p);
        //start the server
        ps.serve();
    }
}
