/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentObject contains a String objects for single comment and user name.
* toString() displays the string on the console
* 
**/

import java.io.Serializable;

// CommentObject.java   Holds information on a single comment
public class CommentObject implements Serializable{
    String name;
    String comment;
    /**
     * Constructor for COmmentObject
     * @param n
     * @param c 
     */
    public CommentObject(String n, String c){
        name=n;
        comment=c;
    }
    /**
     * method to format the output
     * @return 
     */
    @Override
    public String toString(){
        return name + " : " + comment;
    }
    
    

}
