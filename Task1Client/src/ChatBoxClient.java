
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChatBoxClient {

    public static void main(String args[]) throws Exception {
        BufferedReader in =
                new BufferedReader(
                new InputStreamReader(System.in));
        // connect to the rmiregistry and get a remote reference to the Calculator 
        // object.
        ChatBox c = (ChatBox) Naming.lookup("//localhost/CoolChatBox");
        System.out.println("Found ChatBox. Enter ! to quit");
        while (true) {
            try {
                // prompt the user 
                System.out.print("<client>");
                // get a line
                String line = in.readLine();
                // if a "!" is entered just exit
                if (line.equals("!")) {
                    System.exit(0);
                }
                // if it's not a return get the two integers and call add
                // on the remote calculator.
                if (!line.equals("")) {
                    Vector v=c.getMessageArchive(line);
                    for (int i=0;i<v.size();i++){
                        System.out.println(v.get(i));
                    }
                    
                }
            } catch (RemoteException e) {
                System.out.println("allComments: " + e.getMessage());
            }
        }
    }
}