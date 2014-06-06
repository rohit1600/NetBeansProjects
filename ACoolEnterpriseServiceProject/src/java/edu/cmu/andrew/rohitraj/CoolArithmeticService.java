/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.andrew.rohitraj;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rohit
 */
@WebService(serviceName = "CoolArithmeticService")
public class CoolArithmeticService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " Welcome to Web Services!";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        //TODO write your implementation code here:
        return a+b;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sub")
    public int sub(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        //TODO write your implementation code here:
        return a-b;
    }
}
