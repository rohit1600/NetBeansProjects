/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * Web service to access the database and book a trip.
 * involves booking a hotel room, car and a plane seat
 * 
 */
package edu.cmu.andrew.rohitraj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "BookTripWebService")
public class BookTripWebService {

    /**
     * creates hotel, car and plane object
     * reads data based the IDs
     * books rooms, seats and cars by accessing respective methods
     * Transactions follow the ACID rules. Rollsback database when one of the 
     * transaction fails. 
     * @param hotelID
     * @param numRooms
     * @param carID
     * @param numCars
     * @param planeID
     * @param numSeats
     * @return 
     */
    public boolean bookTrip(int hotelID, int numRooms, int carID, int numCars,
            int planeID, int numSeats) {

        Hotel h1 = new Hotel();
        Car c1 = new Car();
        Plane p1 = new Plane();
        Connection con=null;
        try {
            //connection to the database
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con =
                    DriverManager.getConnection("jdbc:derby://localhost:1527/Trip", "rohit", "rohit");
            con.setAutoCommit(false);
            //instantiating object variables with read()
            c1.read(((Integer) carID).toString(), con);
            h1.read(((Integer) hotelID).toString(), con);
            p1.read(((Integer) planeID).toString(), con);
            
            //decreasing the counts of hotel rooms, cars and plane seats
            c1.setCarsAvail(c1.getCarsAvail() - numCars);
            h1.setRoomsAvail(h1.getroomsAvail() - numRooms);
            p1.setSeatsAvail(p1.getSeatsAvail() - numSeats);
            
            //update the database with teh changes
            c1.update(con);
            h1.update(con);
            p1.update(con);
            
            //if all steps before this complete, then commit
            con.commit();

        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            return false;
        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                //rollbakc when there is a problem with any step in try
                con.rollback();
            } catch (Exception ex1) {
                System.out.println("problem rolling back");
                return false;
            }
            return false;
        } 
        return true;
    }
}
