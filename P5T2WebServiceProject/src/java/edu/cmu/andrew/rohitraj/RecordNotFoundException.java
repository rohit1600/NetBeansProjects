package edu.cmu.andrew.rohitraj;
/**
*
* @author mm6
*/

public class RecordNotFoundException extends Exception{
        public RecordNotFoundException() {
    }
        public String toString() {
            return "Could not find database record";
        }
}