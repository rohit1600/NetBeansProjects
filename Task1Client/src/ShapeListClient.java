
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShapeListClient {

        public static void main(String args[]) throws Exception {
        BufferedReader in  = 
                     new BufferedReader(
                         new InputStreamReader(System.in));  
        // connect to the rmiregistry and get a remote reference to the Calculator 
        // object.
        Calculator c  = (Calculator) Naming.lookup("//localhost/CoolCalculator");
 	System.out.println("Found calculator. Enter ! to quit");
   	while(true) {
           try { 
                 // prompt the user 
                 System.out.print("<client>");
                 // get a line
                 String line  = in.readLine();
                 // if a "!" is entered just exit
                 if(line.equals("!")) System.exit(0);
                 // if it's not a return get the two integers and call add
                 // on the remote calculator.
                 if(!line.equals("")) {            
                  c.getMessageArchive(i,j);
                  System.out.println(sum);
                  }
               }
                       
	      catch(RemoteException e) {
                   System.out.println("allComments: " + e.getMessage());
              }
	   }
    }
}
        ShapeList aShapeList = null;
        try {
            aShapeList = (ShapeList) Naming.lookup("//localhost/ShapeList");
            System.out.println("Found server");
            Vector sList = aShapeList.allShapes();
            System.out.println("Got vector");
            if (option.equals("Read")) {
                for (int i = 0; i < sList.size(); i++) {
                    GraphicalObject g = ((Shape) sList.elementAt(i)).getAllState();
                    g.print();
                }
            } else {
                GraphicalObject g = new GraphicalObject(shapeType, new Rectangle(50, 50, 300, 400), Color.red,
                        Color.blue, false);
                System.out.println("Created graphical object");
                aShapeList.newShape(g);
                System.out.println("Stored shape");
            }
        } catch (RemoteException e) {
            System.out.println("allShapes: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lookup: " + e.getMessage());
        }
    }
}
