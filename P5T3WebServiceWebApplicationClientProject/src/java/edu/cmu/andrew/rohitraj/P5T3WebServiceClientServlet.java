/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * This program is a servlet that accesses the web service to access the database
 * and book a trip.
 * The servlet is stand-alone and takes all the requests without the need for a
 * jsp page
 * 
 */
package edu.cmu.andrew.rohitraj;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Rohit
 */
@WebServlet(name = "P5T3WebServiceClientServlet", urlPatterns = {"/P5T3WebServiceClientServlet"})
public class P5T3WebServiceClientServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/P5T2WebServiceProject/BookTripWebService.wsdl")
    private BookTripWebService_Service service;

    /**
     * goGet method handles the http GET requests
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean transaction=false;
        String result=null;
        response.setContentType("text/html;charset=UTF-8");

        //Getting all the parameters from the request
        String hotel_id = (String) request.getParameter("hotel");
        String car_id = (String) request.getParameter("car");
        String plane_id = (String) request.getParameter("plane");
        String num_cars = (String) request.getParameter("num_cars");
        String num_rooms = (String) request.getParameter("num_rooms");
        String num_seats = (String) request.getParameter("num_seats");

        //check for number format exception.
        try{
            //call the bookTrip method
            transaction = bookTrip(Integer.parseInt(hotel_id), Integer.parseInt(num_rooms),
                    Integer.parseInt(car_id), Integer.parseInt(num_cars),
                    Integer.parseInt(plane_id), Integer.parseInt(num_seats));
            //if return value is true, then transaction was successful
            if(transaction==true)
                result="Changes were successful";
            else
                result="Error in input (or) Changes could not be made";
            
        }
        catch(NumberFormatException e){
            //One of the inputs was not a valid ID or amount
            result="Enter a valid number";
        }
        
        //Write html to output
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome to BookMyTrip</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to BookMyTrip</h1>");
            out.println("<form method=\"GET\" action=\"P5T3WebServiceClientServlet\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Enter Hotel Code</td>");
            out.println("<td><input type=\"text\" name=\"hotel\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Enter Number of Rooms</td>");
            out.println("<td><input type=\"text\" name=\"num_rooms\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Enter Car Code</td>");
            out.println("<td><input type=\"text\" name=\"car\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Enter Number of Cars</td>");
            out.println("<td><input type=\"text\" name=\"num_cars\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Enter Plane code</td>");
            out.println("<td><input type=\"text\" name=\"plane\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Enter Number of Seats</td>");
            out.println("<td><input type=\"text\" name=\"num_seats\"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td></td>");
            out.println("<td><input type=\"submit\" name=\"submit\"></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            
            //to eliminate the result message from running for the first visit or
            //when there is no input.
            if(!hotel_id.isEmpty() || car_id.isEmpty() || plane_id.isEmpty() ||
                    num_rooms.isEmpty() || num_cars.isEmpty() || num_seats.isEmpty())
                out.println("<p>"+result+"</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    /**
     * proxy code for the web service method bookTrip()
    */
    private boolean bookTrip(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        edu.cmu.andrew.rohitraj.BookTripWebService port = service.getBookTripWebServicePort();
        return port.bookTrip(arg0, arg1, arg2, arg3, arg4, arg5);
    }
}
