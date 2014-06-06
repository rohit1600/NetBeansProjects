/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple client code to communicate with server 
* Client communicates over port 4444
* Reader and writer objects are created to send streamed messages
* Client listens on port 4444 and responds on the same port
* Client prints server responses on console
*/
package edu.cmu.andrew.rohitraj;

// From Sun Microsystems
import java.io.*;
import java.net.*;

public class RestaurantClient {
    public static void main(String[] args) throws IOException {

        Socket rSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        //Creating client socket on port 4444
        //End execution if connection cannot be established
        try {
            rSocket = new Socket("localhost", 4444);
            out = new PrintWriter(rSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(rSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " +rSocket.getInetAddress());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }
        
        //to receive input from keyboard
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        //Client remains in this loop for entire life
        //exits execution if server response is Bye
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye."))
                break;
		    
            fromUser = stdIn.readLine();
	    if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
	    }
        }

        //Close all connections
        out.close();
        in.close();
        stdIn.close();
        rSocket.close();
    }
}