/**
* Author: Rohit Rajagopal
* Last Modified: Mar 4, 2012
*
* This class starts up the registry. It creates a Binder_Servant and passes that
* servant to its new Binder_Skeleton and calls serve.
*/
package edu.cmu.andrew.rohitraj;

/**
 *
 * @author Rohit
 */
public class BinderServer {
    
    public static void main(String []args){
        Binder b=new Binder_Servant();
        Binder_Skeleton bsk=new Binder_Skeleton(b);
        bsk.serve();
        
    }
    
}
