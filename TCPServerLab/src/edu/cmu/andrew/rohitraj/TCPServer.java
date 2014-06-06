package edu.cmu.andrew.rohitraj;

import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port
            while (true) {
                ServerSocket listenSocket = new ServerSocket(serverPort);
                clientSocket = listenSocket.accept();
                BufferedReader in;
                PrintWriter out;
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
                String temp;
                if ((temp = in.readLine()) != null) {
                    String data = temp;
                    while (!in.readLine().isEmpty()) {
                    }
                    if (!data.isEmpty()) {
                        int startfarm = data.indexOf("/");
                        int endfarm = data.indexOf(" ", startfarm);
                        String file = data.substring(startfarm + 1, endfarm);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String output = "";
                        out.println("HTTP/1.1 200 OK\n\n");
                        while ((output = br.readLine()) != null) {
                            out.println(output);
                        }
                        out.println("\n");
                    }
                    out.flush();
                    out.close();
                    in.close();
                    listenSocket.close();
                    clientSocket.close();
                }
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