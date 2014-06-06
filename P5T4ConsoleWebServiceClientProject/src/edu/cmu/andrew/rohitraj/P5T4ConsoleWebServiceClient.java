/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * This program is a web service client and uses the web service to access the database
 * and book a trip.
 * The main method reads input 6 times for all the parameters and then calls the 
 * remote method bookTrip()
 */
 
package edu.cmu.andrew.rohitraj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5T4ConsoleWebServiceClient {

    /**
     * main method for the web service client
     * @param args 
     */
    public static void main(String[] args) {
        boolean transaction;
        //try for NumberFormatException to check for valid user inputs
        try {

            //Reader to read from the keyboard
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            
            // prompt user for Hotel_ID
            System.out.println("Enter Hotel ID: ");
            //reading the input from the user
            int hotel_id = Integer.parseInt(is.readLine());
            // prompt user for num_rooms
            System.out.println("Enter Number of rooms: ");
            int num_rooms = Integer.parseInt(is.readLine());
            
            // prompt user for Car_ID
            System.out.println("Enter Car ID: ");
            int car_id = Integer.parseInt(is.readLine());
            // prompt user for num_cars
            System.out.println("Enter Number of cars: ");
            int num_cars = Integer.parseInt(is.readLine());
            
            // prompt user for Plane_ID
            System.out.println("Enter Plane ID: ");
            int plane_id = Integer.parseInt(is.readLine());
            // prompt user for num_seats
            System.out.println("Enter Number of seats: ");
            int num_seats = Integer.parseInt(is.readLine());
            
            //calling the web service
            transaction=bookTrip(hotel_id,num_rooms,car_id,num_cars,plane_id,num_seats);
            if(transaction==true)
                System.out.println("Transaction was successful");
            else
                System.out.println("Transaction failed");

        } catch (IOException ex) {
            System.out.println("IOException occurred.");
        } catch (NumberFormatException ex) {
            //this code is run if number is not entered
            System.out.println("Invalid ID. Try Again");
        }

    }

    /**
     * proxy code for the web service
     */
    private static boolean bookTrip(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        edu.cmu.andrew.rohitraj.BookTripWebService_Service service = new edu.cmu.andrew.rohitraj.BookTripWebService_Service();
        edu.cmu.andrew.rohitraj.BookTripWebService port = service.getBookTripWebServicePort();
        return port.bookTrip(arg0, arg1, arg2, arg3, arg4, arg5);
    }
    
}
