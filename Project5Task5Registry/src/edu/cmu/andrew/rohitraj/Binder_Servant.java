/**
* Author: Rohit Rajagopal
* Last Modified: Mar 9, 2012
*
* This class implements the Binder interface
* the treemap contains the String, RemoteObjectReference pairs that server should
* host.
*/
package edu.cmu.andrew.rohitraj;

import java.util.*;

public class Binder_Servant implements Binder{
    
    static TreeMap tm=new TreeMap<String, RemoteObjectReference>();
    
    /**
     * looks up string in map and returns RemoteObjectReference
     */
    public RemoteObjectReference lookup(String s) throws Exception{
        return (RemoteObjectReference) tm.get(s);
    }
    
    /**
     * adds the arguments to the map
     */
    public void bind(String s, RemoteObjectReference ror) throws Exception{
        //adding the arguments to tree map
        tm.put(s, ror);
    }
    
}
