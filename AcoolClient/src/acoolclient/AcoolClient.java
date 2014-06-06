/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acoolclient;

/**
 *
 * @author Rohit
 */
public class AcoolClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(hello("rohit"));
    }

    private static int add(int a, int b) {
        edu.cmu.andrew.rohitraj.CoolArithmeticService_Service service = new edu.cmu.andrew.rohitraj.CoolArithmeticService_Service();
        edu.cmu.andrew.rohitraj.CoolArithmeticService port = service.getCoolArithmeticServicePort();
        return port.add(a, b);
    }

    private static String hello(java.lang.String name) {
        edu.cmu.andrew.rohitraj.CoolArithmeticService_Service service = new edu.cmu.andrew.rohitraj.CoolArithmeticService_Service();
        edu.cmu.andrew.rohitraj.CoolArithmeticService port = service.getCoolArithmeticServicePort();
        return port.hello(name);
    }

    private static int sub(int a, int b) {
        edu.cmu.andrew.rohitraj.CoolArithmeticService_Service service = new edu.cmu.andrew.rohitraj.CoolArithmeticService_Service();
        edu.cmu.andrew.rohitraj.CoolArithmeticService port = service.getCoolArithmeticServicePort();
        return port.sub(a, b);
    }
}
