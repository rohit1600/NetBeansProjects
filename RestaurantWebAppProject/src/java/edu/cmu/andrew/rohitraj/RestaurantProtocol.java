/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple program to compute outgoing message
* State is set by default to waiting
* processInput is the method used to compute outgoing message based on user input and state
*/
package edu.cmu.andrew.rohitraj;

// From Sun Microsystems
import java.net.*;
import java.io.*;
import java.text.DecimalFormat;

public class RestaurantProtocol {
    
    //Collection of states in the restaurant protocol
    private static final int WAITING = 0;
    private static final int SENTMENUQUESTION = 1;
    private static final int READMENU = 2;
    private static final int SENTDESSERTQUESTION = 3;
    private static final int SENTCHECK = 4;
  
    private int state = WAITING;
    private double bill;
    
    //getState method to return the state
    public int getstate(){
        return state;
    }
    
    /**
     * processInput takes the user input and computes the output message
     * Output is based on the current value of state
     */
    public String processInput(String theInput) {

        String theOutput = null;
       
        //DecimalFormat used to format the ouput to 2 decimal places
        DecimalFormat df=new DecimalFormat("##.##");
        
        //Logic implemented through If Else statements to check for state first
        //once state is matched, check for user input and return appropriate output
        //state variable to changed to reflect the next state accordingly
        if (state == WAITING) {
            theOutput = "Would you like a menu!";
            state = SENTMENUQUESTION;
        } else if (state == SENTMENUQUESTION) {
            if (theInput.equalsIgnoreCase("YES")) {
                theOutput = "The menu is steak ($12.43) or pasta ($8.87). Which do you prefer?";
                state = READMENU;
            } else if (theInput.equalsIgnoreCase("no"))
                theOutput="Bye.";
            else {
                theOutput = "Reply in either a \"yes\" or \"no\". " +
			    "Try again. Would you like a menu!";
            }
        } else if (state == READMENU) {
            if (theInput.equalsIgnoreCase("steak") | theInput.equalsIgnoreCase("pasta")) {
                if(theInput.equalsIgnoreCase("steak"))
                    bill=12.43;
                else
                    bill=8.87;
                theOutput = "Cake ($1.45) or Ice Cream ($2.0) for desert?";
                state = SENTDESSERTQUESTION;
            } else {
                theOutput = "You're supposed to say steak or pasta. Which do you prefer?";
                state = READMENU;
            }
        } else if (state == SENTDESSERTQUESTION) {
            if (theInput.equalsIgnoreCase("cake") | theInput.equalsIgnoreCase("ice cream")) {
                if(theInput.equalsIgnoreCase("cake"))
                    bill=bill+1.45;
                else
                    bill=bill+2.0;
                theOutput = "Here is your dessert and your check. Please pay $" + df.format(bill) +" and come again!";
                state = SENTCHECK;
            }
            else{
                theOutput = "Please choose cake or ice cream";
                state=SENTDESSERTQUESTION;
            }
        } else if(state==SENTCHECK){
            if(theInput.equalsIgnoreCase("")){
                theOutput = "Would you like a menu!";
                state = SENTMENUQUESTION;
            }
            else{
                theOutput = "";
                state=SENTCHECK;
            }
        }
        return theOutput;
    }
}