/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 *
 * This program demonstrates a very simple server code to communicate via TCP
 * protocol and send/receive messages. The messages are also encrypted and
 * decrypted using the Caesar Cipher with shift 3. Server communicated on port
 * 7896 Connection class supports threading. caesar3encrypt() method is used to
 * encrypt messages caesar3decrypt() method is used to decrypt messages from
 * server
 *
 */
package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {

    /**
     * main method for server server creates serversocket and listens on port
     * 7896 server creates new thread for each new client connection
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            //server constantly listens on port 7896
            //server creates thread for each incoming connection
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

/**
 * Class to support threading
 *
 * @author Rohit
 */
class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    /**
     * Constructor for Connection class instantiates DataStream objects calls
     * the start() method for each new object
     *
     * @param aClientSocket
     */
    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    /**
     * get() randomly accepts/rejects plan from the client with 50% chance
     * decrypts the incoming string first
     * processes the input string
     * encrypts the output string
     */
    public void run() {
        try {			                 // an echo server
            Random rand = new Random();
            String data = in.readUTF();	                  // read a line of data from the stream
            data = caesar3decrypt(data);
            if (rand.nextInt(2) < 1) {
                out.writeUTF(caesar3encrypt("The plan to " + data + " has been approved"));
            } else {
                out.writeUTF(caesar3encrypt("The plan to " + data + " has been rejected"));
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*
                 * close failed
                 */
            }
        }
    }

    /**
     * method to encrypt Sting inputs using Caesar Cipher
     * String converted to character array and Ascii value manipulated according
     * to logic
     * @param x
     * returns a String
     */
    public static String caesar3encrypt(String x) {
        char y[] = x.toCharArray();
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
        char y[] = x.toCharArray();
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