/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple program to demonstrate communication via sockets
* ServerSocket is created on port 4444
* Server listens on port 4444 and responds on the same port
* Output string is calculated from the Restaurant Protocol fil
*/
package edu.cmu.andrew.rohitraj;

// From Sun Microsystems
import java.net.*;
import java.io.*;

public class RestaurantServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        
        //Creating new ServerSocket on port 4444
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        
        //New socket to stand in for clients
        //socket made to accept incoming connections
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        
        //To output steam to client socket
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        //To read input from client socket
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        RestaurantProtocol kkp = new RestaurantProtocol();
        
        //null value passed to get the welcome message
        outputLine = kkp.processInput(null);
        out.println(outputLine);
        
        //Server remains in while loop and continuously listens to client socket
        while ((inputLine = in.readLine()) != null) {
            
            //outgoing message computed and sent in output stream
             outputLine = kkp.processInput(inputLine);
             out.println(outputLine);
             
             //if output is bye, server stops execution
             if (outputLine.equals("Bye."))
                break;
        }
        
        //closing all sockets and connections
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}