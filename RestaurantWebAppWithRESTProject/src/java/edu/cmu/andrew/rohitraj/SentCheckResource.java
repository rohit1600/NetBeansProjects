/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple RESTful web service with path="SentCheck"
* The user is directed to this page via form submission.
* On submit, the user is sent to the logical next web service with path based on the user input
* next state is mentioned conditionally
*/
package edu.cmu.andrew.rohitraj;

import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Rohit
 */
@Path("SentCheck")
public class SentCheckResource {

    @Context
    private UriInfo context;

    /**
     * The getHtml is used to handle the GET requests.
     * Outgoing message is obtained by passing user input to the restaurant protocol object.
     * HTML code is returned with the outgoing message and text input box
     * HTML form directs to the next WS based on the outgoing message
     * If outgoing message presents the bill, next state is set to Waiting
     * Else state is set to SentCheck
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //Next 5 lines of code extract the user input and the hidden bill parameter
        Map m = context.getQueryParameters();
        List inputList = (List) m.get("input");
        String userInput = (String) inputList.get(0);
        List billList = (List) m.get("bill");
        Double bill= Double.parseDouble((String) billList.get(0));
        
        RestaurantProtocol rp=new RestaurantProtocol();
        
        //setState used to process user input from current state
        rp.setstate(3);
        
        //setBill used to set user's bill so far
        rp.setBill(bill);
        
        String message=rp.processInput(userInput);
        
        //ternary operation to conditionally select the state
        return "<html><head><title>Welxcome to the Restaurant</title></head><body>" + message
                + "<form method='get' action='" + (message.contains("Here is")? "Waiting":"SentCheck")+ "'>"
                + "Input:<input type='text' name = 'input'></input>"
                + "<input type='hidden' name = 'bill' value= "+rp.getBill()+">"
                + "<p><input type = 'submit'></input></p> "
                + "</form></body></html>";
    }

}
