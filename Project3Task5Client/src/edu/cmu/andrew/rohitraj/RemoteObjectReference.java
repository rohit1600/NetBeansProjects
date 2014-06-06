/**
 * Author: Rohit Rajagopal Last Modified: Mar 9, 2012
 * 
* This class implements serializable Instance variables have set and get
 * methods defined
 */
package edu.cmu.andrew.rohitraj;

import java.io.Serializable;

public class RemoteObjectReference implements Serializable {
    //instance variables

    private byte[] inetAddress = new byte[4];
    private int port;
    private int time;           //unused attributte
    private int objectNumber;   //unused attribute
    private String interfaceOR; //unused attribute

    /**
     * returns inetAddress
     */
    public byte[] getInetAddress() {
        return inetAddress;
    }

    /**
     * sets inetAddress
     */
    public void setInetAddress(byte[] x) {
        inetAddress = x;
    }

    /**
     * returns port number
     */
    public int getPort() {
        return port;
    }

    /**
     * set port number
     */
    public void setPort(int x) {
        port = x;
    }

    /**
     * return objectNumber
     */
    public int getObjectNumber() {
        return objectNumber;
    }

    /**
     * set objectNumber
     */
    public void setObjectNumber(int x) {
        objectNumber = x;
    }

    /**
     * return interfaceOR
     */
    public String getInterfaceOR() {
        return interfaceOR;
    }

    /**
     * set interfaceOR
     */
    public void setInterfaceOR(String x) {
        interfaceOR = x;
    }

    /**
     * test method
     *
     * @param main
     */
    public static void main(String[] main) {
    }
}
