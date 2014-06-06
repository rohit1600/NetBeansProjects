/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple RESTful web service with path="Waiting"
* The web service is run with path : http://localhost:64950/RestaurantWebAppWithRESTProject/resources/Waiting
* The welcome page is sent to the browser and text box given for user input
* On submit, the user is sent to the next web service with path = "SentMenu"
*/
package edu.cmu.andrew.rohitraj;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("Waiting")
public class WaitingResource {

        @Context
    private UriInfo context;

    /**
     * The getHtml is used to handle the GET requests.
     * Welcome message is obtained from the restaurant protocol object.
     * HTML code is returned with the message and text input box
     * HTML form directs to the next WS with path="SentMenu"
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        
        //Instantiating object of RestaurantProtocol where the message logic is built
        RestaurantProtocol rp=new RestaurantProtocol();
        return "<html><head><title>Welcome to the Restaurant</title></head><body>" + rp.processInput(null)
                + "<form method='get' action='SentMenuQuestion'>"
                + "Input:<input type='text' name = 'input'></input>"
                + "<p><input type = 'submit'></input></p> "
                + "</form></body></html>";
    }

}
