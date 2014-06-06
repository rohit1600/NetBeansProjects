/**
* Author: Rohit Rajagopal
* Last Modified: April 6, 2012
*
* CommentObject contains a String object for single comment.
* toString() displays the string on the console
* 
**/
import java.io.Serializable;

public class CommentObject implements Serializable{
    String comment;
    
    /**
     * Constructor for comment object
     * @param c 
     */
    public CommentObject( String c){
        comment=c;
    }
    
    /**
     * to format the output of string object
     * @return 
     */
    @Override
    public String toString(){
        return comment;
    }
    
    

}
