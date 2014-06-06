/**
*
* @author mm6
*/
package databasedemoproject;

public class RecordNotFoundException extends Exception{
        public RecordNotFoundException() {
    }
        public String toString() {
            return "Could not find database record";
        }
}