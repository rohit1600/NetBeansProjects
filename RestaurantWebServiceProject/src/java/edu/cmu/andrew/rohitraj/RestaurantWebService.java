/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple web service using SOAP
* userid and user state are stored in a tree map
* another tree map is used to store the outstanding bill amount for each user
* visit method computes output message from processInput() and returns it
* 
*/
package edu.cmu.andrew.rohitraj;

import java.text.DecimalFormat;
import java.util.TreeMap;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rohit
 */
@WebService(serviceName = "RestaurantWebService")
public class RestaurantWebService {
    private static TreeMap tm=new TreeMap();
    private static TreeMap bm=new TreeMap();
    //private double bill;
    /**
     * Web service operation
     */
    @WebMethod(operationName = "visit")
    public synchronized String visit(@WebParam(name = "fromClient") String fromClient, @WebParam(name = "userID") String userID) {
        //TODO write your implementation code here:
        String ret=null;
        if(!tm.containsKey(userID)){
            tm.put(userID, "WAITING");
            bm.put(userID, 0.0);
            ret=processInput("",userID);
        }
        else{
           ret=processInput(fromClient, userID);
        }
        return ret;
    }
    
    /**
     * processInput method takes user input string and userID as inputs
     * computes the output message based on current state
     * updates the state in tree map for corresponding user
     * updates the bill amount in the tree map for corresponding user
     */
    private String processInput(String theInput, String userID) {
        
        String theOutput = null;
        
        //retrieve state from tree map
        String state=(String) tm.get(userID);
        
        //retrieve bill amount from the tree map
        double bill=(Double) bm.get(userID);
        
        //Decimal format used to format bill output to 2 decimal places
        DecimalFormat df=new DecimalFormat("##.##");
        
        //Logic implemented through If Else statements to check for state first
        //once state is matched, check for user input and return appropriate output
        //state variable to changed to reflect the next state accordingly
        //bill and state are saved to the tree maps
        if ("WAITING".equals(state)) {
            theOutput = "Would you like a menu!";
            state = "SENTMENUQUESTION";
            tm.put(userID,state);
        } else if ("SENTMENUQUESTION".equals(state)) {
            if (theInput.equalsIgnoreCase("YES")) {
                theOutput = "The menu is steak ($12.43) or pasta ($8.87). Which do you prefer?";
                state = "READMENU";
                tm.put(userID,state);
            } else if (theInput.equalsIgnoreCase("no")){
                theOutput="Bye.";
                state="WAITING";
                tm.put(userID, state);
            }
            else {
                theOutput = "Reply in either a \"yes\" or \"no\". " +
			    "Try again. Would you like a menu!";
            }
        } else if ("READMENU".equals(state)) {
            if (theInput.equalsIgnoreCase("steak") | theInput.equalsIgnoreCase("pasta")) {
                if(theInput.equalsIgnoreCase("steak"))
                    bm.put(userID, 12.43);
                else
                    bm.put(userID,8.87);
                theOutput = "Cake ($1.45) or Ice Cream ($2.0) for desert?";
                state = "SENTDESSERTQUESTION";
                tm.put(userID,state);
            } else {
                theOutput = "You're supposed to say steak or pasta. Which do you prefer?";
                state = "READMENU";
                tm.put(userID,state);
            }
        } else if ("SENTDESSERTQUESTION".equals(state)) {
            if (theInput.equalsIgnoreCase("cake") | theInput.equalsIgnoreCase("ice cream")) {
                if(theInput.equalsIgnoreCase("cake"))
                    bill=bill+1.45;
                else
                    bill=bill+2.0;
                theOutput = "Here is your desert and your check. Please pay $" + df.format(bill) +" and come again!";
                state = "SENTCHECK";
                tm.put(userID,state);
            }
            else{
                theOutput = "Please choose cake or ice cream";
                state="SENTDESSERTQUESTION";
                tm.put(userID,state);
            }
        } else if("SENTCHECK".equals(state)){
            if(theInput.equals("")){
                theOutput = "Would you like a menu!";
                state = "SENTMENUQUESTION";
                tm.put(userID,state);
            }
            else{
                theOutput = "Please press enter to try again";
                state="SENTCHECK";
                tm.put(userID,state);
            }
        }
        return theOutput;
    }
}

