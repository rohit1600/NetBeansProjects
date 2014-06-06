/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * Web service to access the database.
 * method takes id as argument and returns the customer name
 */
package databasedemoproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "DBWebService")
public class DBWebService {
    
    /**
     * method to return the customer name given the id
     * 
     * @param id
     * @return 
     */
    public String getNameGivenID(@WebParam(name = "id") int id){
        Account a1=new Account();
        try {
            //establish connection to database
            Connection con =
                    DriverManager.getConnection("jdbc:derby://localhost:1527/Account", "rohit", "rohit");
            
            a1.read(((Integer) id).toString() ,con);
            
        } catch (RecordNotFoundException ex) {
            return "Record not found";
        } catch (SQLException ex) {
            Logger.getLogger(DBWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1.getCustomerName();
    }
    
}
