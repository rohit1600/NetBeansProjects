/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 * 
 * This program demonstrates a very simple client code to communicate via TCP
 * protocol and send/receive messages. The messages are also encrypted using the 
 * Caesar Cipher with shift 3.
 * sendReceive() method contains all the socket level programming
 * caesar3encrypt() method is used to encrypt messages
 * caesar3decrypt() method is used to decrypt messages from server
 * Client communicated on port 7896
 */
package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.io.*;

public class TCPClient {

    /**
     * main method representing the client side
     * Command line arguments are the message and 
     * @param args 
     */
    public static void main(String args[]) {
        //call sendReceive with arguments and print output to console
        System.out.println(sendReceive(args[1],args[0]));
    }
    
    /**
     * method contains socket level programming
     * sends input string to server (also mentioned in command line)
     * 
     * @param msg
     * @param server
     * @return 
     */
    public static String sendReceive(String msg, String server){
        String output=null;
        // arguments supply message and hostname
        Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket(msg, serverPort);
            //stream to receive from server
            DataInputStream in = new DataInputStream(s.getInputStream());
            //Stream to send to server
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(caesar3encrypt(server));      	// UTF is a string encoding see Sn. 4.4
            String data = in.readUTF();	    // read a line of data from the stream
            output="The reply from headqaurters is: " + caesar3decrypt(data);
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            //if socket is open, then close it
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return output;
    }

    /**
     * method to encrypt Sting inputs using Caesar Cipher
     * String converted to character array and Ascii value manipulated according
     * to logic
     * @param x
     * returns a String
     */
    public static String caesar3encrypt(String x) {
        char y[]=x.toCharArray();
        for (int i = 0; i < x.length(); i++) {
            int asciivalue = (int) y[i];
            if ((asciivalue >= 65 && asciivalue <= 87) || (asciivalue >= 97 && asciivalue <= 119)) {
                y[i] = (char) (asciivalue + 3);
            } else if ((asciivalue > 87 && asciivalue <= 90) || (asciivalue > 119 && asciivalue <= 122)) {
                y[i] = (char) (asciivalue - 23);
            }
        }
        return (new String(y));
    }
    
    /**
     * method to encrypt Sting inputs using Caesar Cipher
     * String converted to character array and Ascii value manipulated according
     * to logic
     * @param x
     * returns a String
     */
    public static String caesar3decrypt(String x) {
        char y[]=x.toCharArray();
        for (int i = 0; i < x.length(); i++) {
            int asciivalue = (int) y[i];
            if ((asciivalue >= 68 && asciivalue <= 90) || (asciivalue >= 100 && asciivalue <= 122)) {
                y[i] = (char) (asciivalue - 3);
            } else if ((asciivalue >= 65 && asciivalue <= 67) || (asciivalue >= 97 && asciivalue <= 99)) {
                y[i] = (char) (asciivalue + 23);
            }
        }
        return (new String(y));
    }
}