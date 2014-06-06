package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.io.*;

public class TCPClient {

    public static void main(String args[]) {
        // arguments supply hostname
        Socket clientSocket = null;
        try {
            int serverPort = 7777;
            clientSocket = new Socket("localhost", serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));


            BufferedReader typed = new BufferedReader(new InputStreamReader(System.in));
            String m;
            while ((m = in.readLine()) != null) {
                out.println(m);
                out.flush();
                String data = in.readLine(); // read a line of data from the stream
                System.out.println("Received: " + data);
            }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
}