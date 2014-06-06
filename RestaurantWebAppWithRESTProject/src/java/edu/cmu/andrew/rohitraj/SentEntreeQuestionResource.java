/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple RESTful web service with path="SentEntreeQuestion"
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

@Path("SentEntreeQuestion")
public class SentEntreeQuestionResource {

    @Context
    private UriInfo context;

    /**
     * The getHtml is used to handle the GET requests.
     * Outgoing message is obtained by passing user input to the restaurant protocol object.
     * HTML code is returned with the outgoing message and text input box
     * HTML form directs to the next WS based on the outgoing message
     * If outgoing message is the Dessert options, next state is set to SentCheck
     * Else state is set to SentEntreeQuestion
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //The next three lines fetch the user input
        Map m = context.getQueryParameters();
        List inputList = (List) m.get("input");
        String userInput = (String) inputList.get(0);
        
        RestaurantProtocol rp=new RestaurantProtocol();
        
        //setState used to process user input from current state
        rp.setstate(2);
        String message=rp.processInput(userInput);
        
        //ternary operation to conditionally select the state
        //bill amount is passed as a hidden parameter to be used in the next state
        return "<html><head><title>Welcome to the Restaurant</title></head><body>" + message
                + "<form method='get' action='" + (message.contains("Cake ($1.45)")? "SentCheck": "SentEntreeQuestion")+ "'>"
                + "Input:<input type='text' name = 'input'></input>"
                + "<input type='hidden' name = 'bill' value= "+rp.getBill()+">"
                + "<p><input type = 'submit'></input></p> "
                + "</form></body></html>";
    }
}
