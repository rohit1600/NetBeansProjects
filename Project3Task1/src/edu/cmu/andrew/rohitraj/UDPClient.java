/**
 * Author: Rohit Rajagopal Last Modified: Mar 1, 2012
 * 
* This program demonstrates a very simple client code to communicate with UDP
 * protocol and perform arithmetic operations. 
 * The program takes the inputs for arithmetic operation from command
 * line. Client communicates over port 6789. Datagram Packet with message is
 * created and sent to server. Datagram Socket object is created to listen on
 * port.
 */
package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.io.*;

public class UDPClient {

    /**
     * inputs for arithmetic operation are provided in commandline
     * listens to response from server and displays on console
     */
    public static void main(String args[]) {

        //Datagram Socket object to communicate with server
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            
            //input argument String is converted to byte[]
            byte[] m = (args[0] + " " + args[1] + " " + args[2]).getBytes();
            
            //Since server is localhost
            InetAddress aHost = InetAddress.getByName("localhost");
            
            //6789 is the chosen port for communication
            int serverPort = 6789;
            
            //UDP packet is created with message and server information
            DatagramPacket request = 
                    new DatagramPacket(m, m.length, aHost, serverPort);
            
            //sending message
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            
            //datagram packet to receive response from server
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            
            System.out.println("Reply: " + new String(reply.getData()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                //close socket -- best practice
                aSocket.close();
            }
        }
    }
}
