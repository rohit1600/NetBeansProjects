/**
* Author: Rohit Rajagopal
* Last Modified: Apr 27, 2012
*
* Objects of this class are passed to initiate the snapshot
*/
package edu.cmu.heinz.ds.pit;
import java.io.Serializable;

/*
 * A Marker, as used in the snapshot algorithm.
 */
public class Marker implements Serializable {
    // source is the Player number from which the Marker is sent
    int source;

    /**
     * Constructor for the Makrker class
     * @param source 
     */
    public Marker(int source) {
        this.source = source;
    }
}
