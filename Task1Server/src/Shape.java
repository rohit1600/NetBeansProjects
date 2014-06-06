

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;
import java.util.Vector;

public interface Shape extends Remote {
   int getVersion() throws RemoteException;
   GraphicalObject getAllState() throws RemoteException;
}