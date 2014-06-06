/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpasoapclientproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Rohit
 */
public class JPASOAPClientProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter customer ID");
        String line = is.readLine();
        int val = Integer.parseInt(line);
        String answer = customerName(val);
        System.out.println(answer);
    }

    private static String customerName(int x) {
        edu.cmu.andrew.rohitraj.FirstRecordWS_Service service = new edu.cmu.andrew.rohitraj.FirstRecordWS_Service();
        edu.cmu.andrew.rohitraj.FirstRecordWS port = service.getFirstRecordWSPort();
        return port.customerName(x);
    }
}
