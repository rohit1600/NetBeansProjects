/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 * 
* This program demonstrates a very simple server code to communicate via UDP
 * protocol. The server takes a string argument representing an arithmetic
 * operation. The operation can +,-,* or /. Server communicates over port 6789.
 * Datagram Packet with result of arithmetic is created and sent to clients.
 */
package edu.cmu.andrew.rohitraj;

/**
 *
 * @author Rohit
 */
import java.net.*;
import java.io.*;

public class UDPServer {

    /**
     * main() method of the server. No command line arguments. Datagram socket
     * to listen on port 6789. Operation and Operands are extracted from input
     * string and processed accordingly.
     */
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port

            while (true) {
                byte[] buffer = new byte[1000];

                //Datagram packet to receive incoming packet
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                //trim() used to eliminate leading and lagging spaces
                String input = (new String(buffer)).trim();

                //extracting operands
                int op1 = Integer.parseInt(input.substring(0, input.indexOf(' ')));
                int op2 = Integer.parseInt(input.trim().substring(input.lastIndexOf(' ') + 1));
                double output = 0;

                //Switch-case to perform arithmetic operation after extracting 
                //operation from string
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
                DatagramPacket reply = new DatagramPacket(((Double) output).toString().getBytes(), ((Double) output).toString().getBytes().length,
                        request.getAddress(), request.getPort());
                aSocket.send(reply);
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