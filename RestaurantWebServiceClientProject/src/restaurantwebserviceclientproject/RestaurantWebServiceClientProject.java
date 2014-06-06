/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program forms a client for demonstration of a very simple web service using SOAP
* 
* 
*/
package restaurantwebserviceclientproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestaurantWebServiceClientProject {

    /**
     * main method to test the client program
     */
    public static void main(String []args) throws IOException {
        
        String fromUser=null;
        String fromServer=null;
        
        //Reader to buffer user input from keyboard
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        
        //command line aargument to be the userid
        String userID=args[0];        
        
        //Client stays in this loop and executes the protocol
        //whenever there is a user input, the visit method is called
        while ((fromServer = visit(fromUser,userID)) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye."))
                break;
		    
            fromUser = is.readLine();
	    if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                
	    }
        }
    }

    
    /**
     * visit method is the remote invocation of the visit method in the SOAP web service
     * method takes user input and userID as parameters
     */
    private static String visit(java.lang.String fromClient, java.lang.String userID) {
        edu.cmu.andrew.rohitraj.RestaurantWebService_Service service = new edu.cmu.andrew.rohitraj.RestaurantWebService_Service();
        edu.cmu.andrew.rohitraj.RestaurantWebService port = service.getRestaurantWebServicePort();
        return port.visit(fromClient, userID);
    }

    
}
