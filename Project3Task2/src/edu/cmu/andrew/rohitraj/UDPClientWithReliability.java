/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 * 
 * This program demonstrates a very simple client code to communicate with UDP
 * protocol and perform arithmetic operations: 1+2+3...100.
 * The client has a UDP timeout characteristic of 2000 ms.
 * Client communicates over port 6789. Datagram Packet with message is
 * created and sent to server. Datagram Socket object is created to listen on
 * port.
 */
package edu.cmu.andrew.rohitraj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Rohit
 */
public class UDPClientWithReliability {
    /**
     * main method to perform arithmetic: 1+2+3...100
     * No command line arguments
     * @param args 
     */
    public static void main(String []args){
        int output=0;
        for(int i=1; i<=100;i++){
            //add numbers to existing sum
            output=add(output,i);
        }
        System.out.println(output);
    }
    /**
     * inputs are 2 integers 
     * returns integer value of sum.
     * 
     */
    public static int add(int x, int y){
        DatagramSocket aSocket = null;
        int output=0;
		try {
                        aSocket = new DatagramSocket();   
                        
                        //concatenate operands with + and convert to byte[]
			byte [] m = (x+" " + "+" + " " +y).getBytes();
                        
                        //server is in localhost and port 6789
                	InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;		             
                        
                        //Datagram request packet containing the message and 
                        //server details
			DatagramPacket request =
			 	new DatagramPacket(m,  m.length, aHost, serverPort);
                        
                        aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
                        //Setting timeout to 2000 ms 
                        aSocket.setSoTimeout(2000);
                        aSocket.receive(reply);
                        //trim() to remove leading and lagging spaces
                        //Since server returns a double value, typecast it to integer
                        output = (int) Double.parseDouble(new String(buffer).trim());
               }catch (SocketException e){
                   System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){
                    //when packet times out, the method is called again for 
                    //same set of integers.
                    output=add(x,y);
		}finally {
                    if(aSocket != null) aSocket.close();
                }
        return output;
    }
    
}
