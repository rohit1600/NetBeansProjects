/**
* Author: Rohit Rajagopal
* Last Modified: Feb 8, 2012
*
* This servlet checks if input string is palindrome
* Servlet output is based on the device from which request is made. 
* web.xml is configured to make Palin as the welcome file. This ensures that 
* the index page also is adapted to android screen.
*/
package Palin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Palin", urlPatterns = {"/Palin"})
public class Palin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String search= (String) request.getParameter("pal");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        // use model to do the search and choose the result view
        if (ua != null && ua.indexOf("Android") != -1) {
            out.println("<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">");
        } else {
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }     
        String palin=search;
        char[] x= palin.toCharArray();
        int length=palin.length();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Palindrome</title>");            
        out.println("</head>");
        out.println("<body>");
        String result="a Palindrome";
        
        //check if string is not palindrome
        for (int i=0; i<length/2;i++){
            if(x[i]!=x[length-1-i]){
                result= "Not a Palindrome";
                break;
            }
        }
        out.println("<h1>\"" + palin + "\" is " +result + "</h1><br><br>");
        out.println("<form action=\"Palin\" method=\"GET\">");
        out.println("Enter Another String");
        out.println("<input type=\"text\" name=\"pal\">");
        out.println("<input type=\"submit\" value=\"submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
