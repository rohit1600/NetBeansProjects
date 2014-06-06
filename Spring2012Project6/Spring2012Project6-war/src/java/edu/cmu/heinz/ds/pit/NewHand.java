/**
* Author: Rohit Rajagopal
* Last Modified: Apr 27, 2012
*
* Objects of this class are passed to instantiate players with commodities
* for trading
*/
package edu.cmu.heinz.ds.pit;

import java.io.Serializable;
import java.util.ArrayList;

/* 
 * A representation of a new hand of commodities to be sent from PITinit to 
 * each Player
 */
public class NewHand implements Serializable{
    // A list of the new commodity cards
    public ArrayList handCard = new ArrayList();
    
    // The total number of players who will be trading
    public int numPlayers;
}
