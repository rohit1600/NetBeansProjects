/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * This program contains the getter and setter methods for all the attributes of
 * the Hotel table in the database. In addition the program contains methods
 * create -- to add records to the table, update -- to update records,
 * delete -- to delete records or the table entirely based on arguments,
 * read -- to read records from the Hotel table in database.
 */
package edu.cmu.andrew.rohitraj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Hotel {

    private String ID;
    private String name;
    private String location;
    private String URL;
    private int rooms_avail;
    
    /**
     * method to format the output
     * @return 
     */
    @Override
    public String toString() {
        return "ID: " + ID + "\t" + "Name: " + name + "\t" + "Location: "
                + location + "\t" + "URL: "+URL + "\t" + 
                "Rooms Available: " + rooms_avail;
    }

    /**
     * method to return ID
     * @return 
     */
    public String getID() {
        return ID;
    }

    /**
     * method to return name
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * method to set name
     * @param c 
     */
    public void setName(String c) {
        name = c;
    }

    /**
     * method to get location
     * @return 
     */
    public String getLocation() {
        return location;
    }

    /**
     * method to set location
     * @param c 
     */
    public void setLocation(String c) {
        location = c;
    }
    
    /**
     * method to get URL
     * @return 
     */
    public String getURL() {
        return URL;
    }

    /**
     * method to set URL
     * @param c 
     */
    public void setURL(String c) {
        URL = c;
    }
    
    /**
     * method to return rooms available
     * @return 
     */
    public int getroomsAvail() {
        return rooms_avail;
    }
    /**
     * method to set rooms available
     * @param c 
     */
    public void  setRoomsAvail(int c) {
        rooms_avail=c;
    }

    /**
     * method to create records and insert into the table
     */
    public void create(String ID, String name, String location, String URL,
            int rooms_avail, Connection con) throws SQLException, Exception {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.URL=URL;
        this.rooms_avail=rooms_avail;
        PreparedStatement statement = null;
        try {

            statement = con.prepareStatement(
                    "Insert into ROHIT.Hotel (ID," + "name, location, URL, rooms_avail)" + "Values ( ?,?,?,?,?)");
            statement.setString(1, ID);
            statement.setString(2, name);
            statement.setString(3, location);
            statement.setString(4, URL);
            statement.setInt(5, rooms_avail);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Caught exception in create" + e);
            throw new Exception(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * method to read records from the Account table given the accountID
     */
    public void read(String ID, Connection con) throws SQLException,
            RecordNotFoundException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(
                    "Select name, location, URL, rooms_avail FROM ROHIT.Hotel" + " where ID = ?");
            statement.setString(1, ID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                this.ID = ID;
                this.name = result.getString(1);
                this.location = result.getString(2);
                this.URL=result.getString(3);
                this.rooms_avail=result.getInt(4);
            } else {
                System.out.println("Could not read a record");
                throw new RecordNotFoundException();
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * method to update the records in the table using the instance variables
     * account id, amount and name
     */
    public void update(Connection con) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(
                    "Update ROHIT.Hotel set name =  ? ," + "location = ?, URL = ? ,"
                    + "rooms_avail = ?" + "where ID = ?");
            statement.setString(1, name);
            statement.setString(2, location);
            statement.setString(3, URL);
            statement.setInt(4, rooms_avail);
            statement.setString(5, ID);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * method to delete record using the instance variable account id
     */
    public void delete(Connection con) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Delete from ROHIT.Hotel Where ID =  ?");
            statement.setString(1, ID);
            int h = statement.executeUpdate();
            System.out.println("Tried to delete " + ID + " Changed " + h + "s");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * method to delete from table given the account id as the argument
     */
    
    public void delete(String ID, Connection con) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(
                    "Delete from ROHIT.CAR Where ID =  ?");
            statement.setString(1, ID);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

}