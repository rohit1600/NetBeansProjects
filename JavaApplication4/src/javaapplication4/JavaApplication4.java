/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rohit
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List al= new ArrayList();
        int[] x = {1, 2, 1, 1, 7, 4, 5, 3, 2, 6, 4, 6, 4, 3, 3, 6,1,1,2,7};
        al.add(x);
        Arrays.sort(x);
        //System.out.println(x[4]);
        try{
            int i;
        for (i=0;i<x.length;i=i){
            if (x[i]==x[i+2])
                    {
                      i=i+2;  
                    }
            else if (x[i]==x[i+1])
            {
                System.out.println(x[i] + " is the anomaly");
                break;
            }
            else i=i+1;
            
        }
        }
        catch (ArrayIndexOutOfBoundsException a){
            System.out.println(a.getMessage());
            System.out.println(x[Integer.parseInt(a.getMessage())-2] + " is the anomaly");
        }
        
    }
}
