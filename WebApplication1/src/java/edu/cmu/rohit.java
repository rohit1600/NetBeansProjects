/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rohit
 */
@WebService(serviceName = "rohit")
public class rohit {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
