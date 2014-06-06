/**
* Author: Rohit Rajagopal
* Last Modified: Feb 8, 2012
*
* This servlet performs hashing based on selection in index page
* Servlet employs Java Crypto API to compute MD5 or SHA-1 hashing
* Base64Encoder class is used to compute Base64 in result
* Hexadecimal is computed by method getHexString()
* 
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

@WebServlet(name = "ComputeHashes", urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text=(String) request.getParameter("text");
        String hash= (String) request.getParameter("group1");
        PrintWriter out = response.getWriter();
        try {
            
            //creating MessageDigest object with the hash function
            MessageDigest md=MessageDigest.getInstance(hash);
            
            //creating object of Base64Encoder
            BASE64Encoder be=new BASE64Encoder();
            
            //computing the hash value textDigest
            md.update(text.getBytes());
            byte[] textDigest=md.digest();
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ComputeHashes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hash Values are </h1> <br>");
            if("SHA-1".equals(hash)){
                out.println("SHA-1 (Base64) : " +be.encode(textDigest) + "<br>" );
                out.println("SHA-1 (Hex) : " + getHexString(textDigest).toUpperCase() + "<br>" );
            }
            else{
                out.println("MD5 (Base64) : " +be.encode(textDigest) +"<br>");
                out.println("MD5 (Hex) : " + getHexString(textDigest).toUpperCase() + "<br>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
    
    //method to compute hexadecimal string for the hashed value
    public String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring( 1 );
        }
        return result;
    }

}
