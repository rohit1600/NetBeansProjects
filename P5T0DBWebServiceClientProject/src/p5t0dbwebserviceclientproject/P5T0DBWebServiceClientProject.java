/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * This program is a web service client and uses the web service to access the database.
 * The main method reads input (account id) from user and gets the customer name
 */
package p5t0dbwebserviceclientproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohit
 */
public class P5T0DBWebServiceClientProject {

    /**
     * main method reads input from user and displays the customer name
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            // prompt user for ID
            System.out.println("Enter ID: ");
            //Reader to read from the keyboard
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //reading the input from the user
            int id = Integer.parseInt(is.readLine());
            //displaying the Customer Name corresponding to the ID
            System.out.println(getNameGivenID(id));


        } catch (IOException ex) {
            System.out.println("IOException occurred.");
        }
        catch (NumberFormatException ex) {
            //this code is run if number is not entered
            System.out.println("Invalid ID");
        }

    }

    /**
     * method behaves as a proxy code for the method getNameGivenID()
     * 
     * @param id
     * @return 
     */
    private static String getNameGivenID(int id) {
        p5t0dbwebserviceclientproject.DBWebService_Service service = new p5t0dbwebserviceclientproject.DBWebService_Service();
        p5t0dbwebserviceclientproject.DBWebService port = service.getDBWebServicePort();
        return port.getNameGivenID(id);
    }

    
}
