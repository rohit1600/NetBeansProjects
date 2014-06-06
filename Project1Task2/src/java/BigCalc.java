/**
* Author: Rohit Rajagopal
* Last Modified: Feb 8, 2012
*
* This servlet performs one of six arithmetic operations
* addition, multiplication, Relatively prime, modulus, modulus inverse, exponentia
* Servlet catches all exceptions thrown during the arithmetic
* Servlet uses the BigInteger class for the operands and results
* 
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BigCalc", urlPatterns = {"/BigCalc"})
public class BigCalc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        BigInteger x,y;
        try {
            
            x=new BigInteger((String)request.getParameter("x"));
            y=new BigInteger((String)request.getParameter("y"));
            String operation=request.getParameter("operation");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BigCalc</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Calculated output is : </h1>");
            if(operation.equals("add"))
                out.println("X + Y = " + x.add(y).toString());
            else if(operation.equals("multiply"))
                out.println("X * Y = " + x.multiply(y).toString());
            else if(operation.equals("relativelyPrime"))
                out.println("X and Y " + (x.gcd(y).intValue()==1? "are ":"are not ") + "Relatively Prime") ;
            else if(operation.equals("mod"))
                out.println("X % Y = " + x.mod(y).toString());
            else if(operation.equals("modInverse"))
                out.println(" (X ^ -1) % Y = " + x.modInverse(y).toString());
            else if(operation.equals("power"))
                out.println("X ^ Y = " + x.pow(y.intValue()).toString());
            out.println("</body>");
            out.println("</html>");
        } 
        catch (NumberFormatException e){
            out.println("<html><title>Error</title></html>");
            out.println("<h1>Number Format Error </h1>");
            out.println("</html>");
        }
        catch (ArithmeticException e){
            out.println("<html><title>Error</title></html>");
            out.println("<h1>Negative exponent</h1>");
            out.println("</html>");
        }
        catch (Exception e){
            out.println("<html><title>Error</title></html>");
            out.println("<h1> Calculation Overflow </h1>");
            out.println("</html>");
        }
        finally {            
            out.close();
        }
    }
}
