/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 * 
 * This program demonstrates a very simple server code to communicate with UDP
 * protocol and perform arithmetic operations: +,-,* and /
 * The server randomly ignores 70% of the requests.
 * Server communicates over port 6789.
 */
package edu.cmu.andrew.rohitraj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;

/**
 *
 * @author Rohit
 */
public class UDPServerThatIgnoresYou {

    /**
     * main method for the UDP server program.
     * method randomly ignores 70% of the requests.
     * No command line arguments
     */
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Random rnd = new Random();
        try {
            // create socket at agreed port
            aSocket = new DatagramSocket(6789);
            
            while (true) {
                byte[] buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                
                //this loop will be run only 3 out of 10 times
                if (rnd.nextInt(10) < 3) {
                    //converting input byte[] to String and removing spaces
                    String input = (new String(buffer)).trim();
                    
                    //Extracting the operands from string
                    int op1 = Integer.parseInt(input.substring(0, input.indexOf(' ')));
                    int op2 = Integer.parseInt(input.trim().substring(input.lastIndexOf(' ') + 1));
                    double output = 0;
                    
                    //switch case to perform arithmetic operations
                    switch (input.charAt(input.indexOf(' ') + 1)) {
                        case '+':
                            output = op1 + op2;
                            break;
                        case '-':
                            output = op1 - op2;
                            break;
                        case '/':
                            output = (double) op1 / op2;
                            break;
                        case 'x':
                            output = op1 * op2;
                            break;
                        case 'X':
                            output = op1 * op2;
                            break;
                        default:
                            output = 999;
                            break;
                    }
                    //Datagram packet to represent output packet
                    DatagramPacket reply = new DatagramPacket(((Double) output).toString().getBytes(), ((Double) output).toString().getBytes().length,
                        request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }
                else{
                }
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
