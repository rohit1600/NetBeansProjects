/**
 * Author: Rohit Rajagopal Last Modified: Mar 1, 2012
 * 
* This program demonstrates a very simple client code to communicate with UDP
 * protocol and perform addition of 1+2+3...100. Add method in server
 * implemented as proxy. Client communicates over port 6789. Datagram Packet
 * with message is created and sent to server. Datagram Socket object is created
 * to listen on port.
 */
package Task1a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Rohit
 */
public class UDPClientWithProxy {

    /**
     * No command line arguments calls add() method 100 times to perform
     */
    public static void main(String[] args) {
        int output = 0;

        //Iterating 100 times and adding to output
        for (int i = 1; i <= 100; i++) {
            output = add(output, i);
        }
        System.out.println(output);
    }

    /**
     * add() method takes operands as input
     * returns the sum of operands
     * Contains all the socket level programming
     */
    public static int add(int x, int y) {
        DatagramSocket aSocket = null;
        int output = 0;
        try {
            
            //Socket to communicate with server
            aSocket = new DatagramSocket();
            
            //Converting arithmetic string to byte array
            byte[] m = (x + " " + "+" + " " + y).getBytes();
            
            //since server is localhost and port used is 6789
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;
            
            //Datagram packet containing message and server information
            DatagramPacket request =
                    new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            
            //response packet from server received and stored
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            
            //trim() operation used to remove lagging and leading spaces
            //Since server returns a double value, typecast it to integer
            output = (int) Double.parseDouble(new String(buffer).trim());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
        return output;
    }
}
