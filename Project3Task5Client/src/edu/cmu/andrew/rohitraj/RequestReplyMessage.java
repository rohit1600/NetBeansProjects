/**
* Author: Rohit Rajagopal
* Last Modified: Mar 9, 2012
* 
* This class implements serializable
* Instance variables have set and get methods defined
*/
package edu.cmu.andrew.rohitraj;

import java.io.*;

public class RequestReplyMessage implements Serializable {
    
    //instance variables
    private int messageType;
    private int messageID=0;
    private RemoteObjectReference objectReference;
    private int methodID;
    private  byte[] arguments;
    
    /**
     * return messageType
     */
    public int getMessageType(){
        return messageType;
    }
    
    /**
     * return messageID
     */
    public int getMessageID(){
        return messageID;
    }
    
    /**
     * return objectReference
     */
    public RemoteObjectReference getObjectReference(){
        return objectReference;
    }
    
    /**
     * return methodID
     */
    public int getMethodID(){
        return methodID;
    }
    
    /**
     * return arguments
     */
    public byte[] getArguments(){
        return arguments;
    }
    
    /*
     * set messageType
     */
    public void setMessageType(int x){
        messageType=x;
    }
    
    /*
     * set messageID
     */
    public void setMessageID(int x){
        messageID=x;
    }
    
    /*
     * set objectReference
     */
    public void setObjectReference(RemoteObjectReference x){
        objectReference=x;
    }
    
    /*
     * set methodID
     */
    public void setMethodID(int x){
        methodID=x;
    }
    
    /*
     * set arguments
     */
    public void setArguments(byte[] x){
        arguments=x;
    }
}
