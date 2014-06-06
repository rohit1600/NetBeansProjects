/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.andrew.rohitraj;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohit
 */
public class PersonClient {
    public static void main(String []args){
        try {
            Binder_Stub bs=new Binder_Stub();
            RemoteObjectReference r=bs.lookup("Mike");
            Person p=new Person_Stub(r);
            System.out.println(p.getID()+" " + p.getName());
        } catch (Exception ex) {
            Logger.getLogger(PersonClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
