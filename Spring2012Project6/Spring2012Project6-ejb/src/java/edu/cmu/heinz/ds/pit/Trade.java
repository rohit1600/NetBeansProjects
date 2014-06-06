/**
* Author: Rohit Rajagopal
* Last Modified: Apr 27, 2012
*
* Objects of this class are passed to enable a trade
* contains the trader and the commodity (card)
*/
package edu.cmu.heinz.ds.pit;

import java.io.Serializable;

/* 
 * A Trade carries a commodity card from one Player to another.
 */
public class Trade implements Serializable {
    // The Player originating the Trade
    public int sourcePlayer;
    
    // The commodity being traded
    public String tradeCard;    
}
