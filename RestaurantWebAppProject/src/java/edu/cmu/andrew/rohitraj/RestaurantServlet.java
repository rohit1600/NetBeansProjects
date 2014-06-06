/**
* Author: Rohit Rajagopal
* Last Modified: Feb 24, 2012
*
* This program demonstrates a very simple program to handle Http GET requests
* New users to the website get a new RestaurantProtocol object assigned
* for existing users, the session attribute is fetched and output message computed accordingly
* HTML page is output to the browser after computation of output message
* output message is computed via RestaurantProtocol method processInput()
*/
package edu.cmu.andrew.rohitraj;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RestaurantServlet", urlPatterns = {"/RestaurantServlet"})
public class RestaurantServlet extends HttpServlet {
    
    
    /**
     * doGet method to handle GET requests
     * @throws IOException 
     * New users are assigned session ID's via HttpSession
     * Returning users are handled by extracting the session attribute
     * 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RestaurantProtocol rp=null;
        response.setContentType("text/html;charset=UTF-8");
       
        //session to store session attributes pertaining to different users
        HttpSession session=request.getSession();
        String userMessage="";
        
        //If new user, session attribute is set
        if (session.getAttribute("rp")==null){
            rp=new RestaurantProtocol();
            userMessage=rp.processInput(null);
            session.setAttribute("rp", rp);
        }
        
        //else session attribute is extracted and processInput method is called on it
        //User input is passed to the processInput
        else{
           userMessage=((RestaurantProtocol) session.getAttribute("rp")).processInput((String) request.getParameter("userinput"));
        }
        PrintWriter out = response.getWriter();
            
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome to the Restaurant</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //If output is not Bye, then display output message with text input box
            //else display link to try again
            if(!userMessage.equals("Bye.")){
                out.println("<form method=\"GET\" action=\"RestaurantServlet\">");
                out.println("<table><tr><td><h1>" + userMessage + "</h1></td><tr><td><input type=\"text\" name=\"userinput\"></td></tr>");
                out.println("<tr><td><input type=\"submit\" value=\"submit\">");
            }
            else{
                out.println("<h1>" + userMessage + "</h1>");
                session.setAttribute("rp", null);
                out.println("<a href=\"http://localhost:64950/RestaurantWebAppProject/RestaurantServlet\">Try Again</a>");
            }
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
}

