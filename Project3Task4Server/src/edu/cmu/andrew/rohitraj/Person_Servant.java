/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class implements the Person interface
* Contains instance variables id and name
* Contains methods getID and getName to get 
*/
package edu.cmu.andrew.rohitraj;

public class Person_Servant implements Person {

    int id;
    String name;

    /**
     * Constructor for class Person_Servant
     * @param n
     * @param i 
     */
    public Person_Servant(String n, int i) {
        name = n;
        id = i;
    }

    /**
     * returns ID variable of object
     * @return 
     */
    public int getID() {
        return id;
    }
    /**
     * returns name variable from object
     * @return 
     */
    public String getName() {
        return name;
    }
}
