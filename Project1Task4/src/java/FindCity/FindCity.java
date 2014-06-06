/**
* Author: Rohit Rajagopal
* Last Modified: Feb 8, 2012
*
* This servlet implements the MVC framework to generate URLs of city metro map
* Servlet extracts city from the request and calls the getCityMap method
* Servlet then sets attributes and calls the "result.jsp" page
*/
package FindCity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import FindCity.FindCityModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FindCity", urlPatterns = {"/FindCity"})
public class FindCity extends HttpServlet {
    FindCityModel fcm=null;
    
    @Override
    public void init(){
        fcm=new FindCityModel();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String city=request.getParameter("city");
        
        //executed if the city field is not empty
        if( !city.isEmpty()){
            request.setAttribute("city", city);
            request.setAttribute("error","");
            request.setAttribute("cityURL", fcm.getCityMap(city));
            request.setAttribute("linkBackURL",fcm.getlinkBackURL());
            request.setAttribute("message", "Here is the metro map of " + city);
            
            //executed if the city searched is not found on urbanrail.net
            //in such a case, URl returned is a google search page
            if (fcm.getlinkBackURL().contains("http://www.google")){
                request.setAttribute("cityURL", "");
                request.setAttribute("linkBackURL","");
                request.setAttribute("error", "City Not Found");
                request.setAttribute("message","");
            }
                
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
        }
        
        //executed if city field is null
        else{
            RequestDispatcher view=request.getRequestDispatcher("SearchOther.jsp");
            view.forward(request, response);
        }
    }

}
