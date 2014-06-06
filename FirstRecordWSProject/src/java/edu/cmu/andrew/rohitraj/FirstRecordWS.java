/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.andrew.rohitraj;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Rohit
 */
@WebService(serviceName = "FirstRecordWS")
public class FirstRecordWS {

    @PersistenceUnit EntityManagerFactory emf;
    @WebMethod(operationName = "CustomerName")
    public String CustomerName(@WebParam(name = "x") int x) {
        //TODO write your implementation code here:
        Query queryCustomerByID = emf.createEntityManager().createNamedQuery("Customer.findByCustomerId");
            queryCustomerByID.setParameter("customerId", new Integer(x));
            Customer customer = (Customer) queryCustomerByID.getResultList().get(0);
        return customer.getName();
    }
}
