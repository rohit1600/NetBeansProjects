/**
 * Author: Rohit Rajagopal Last Modified: Mar 4, 2012
 * 
* This class defines the interface Binder. This interface has 2 methods
 * lookup() and bind()
 */
package edu.cmu.andrew.rohitraj;

public interface Binder {

    public RemoteObjectReference lookup(String s) throws Exception;

    public void bind(String s, RemoteObjectReference ror) throws Exception;
}
