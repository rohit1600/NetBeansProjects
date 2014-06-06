/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple RESTful web service with path="SentMenuQuestion"
* The user is directed to this page via form submission.
* On submit, the user is sent to the logical next web service with path based on the user input
* next state is mentioned conditionally
*/
package edu.cmu.andrew.rohitraj;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.*;

@Path("SentMenuQuestion")
public class SentMenuQuestionResource {

    @Context
    private UriInfo context;
    /**
     * The getHtml is used to handle the GET requests.
     * Outgoing message is obtained by passing user input to the restaurant protocol object.
     * HTML code is returned with the outgoing message and text input box
     * HTML form directs to the next WS based on the outgoing message
     * If outgoing message is Bye, then link is provided to try again
     * If outgoing message is the Menu options, next state is set to SentEntreeQuestion
     * Else state is set to SentMenuQuestion
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        
        //Next three lines used to get the user input query
        Map m = context.getQueryParameters();
        List inputList = (List) m.get("input");
        String userInput = (String) inputList.get(0);
        
        RestaurantProtocol rp=new RestaurantProtocol();
        
        //setState used to process user input from current state
        rp.setstate(1);
        
        //this string is the outgoing message
        String message=rp.processInput(userInput);
        
        if(message.equalsIgnoreCase("Bye.")){
            return "<html><head><title>Welcome to the Restaurant</title></head><body>" + message
                + "<br><a href=\"http://localhost:64950/RestaurantWebAppWithRESTProject/resources/Waiting\">Try Again</a>"
                + "</form></body></html>";
        }
        
        //Ternary operator used to conditionally set the next state
        return "<html><head><title>Welcome to the Restaurant</title></head><body>" + message
                + "<form method='get' action='" + (message.contains("The menu is")? "SentEntreeQuestion":"SentMenuQuestion")+ "'>"
                + "Input:<input type='text' name = 'input'></input>"
                + "<p><input type = 'submit'></input></p> "
                + "</form></body></html>";
    }

}
