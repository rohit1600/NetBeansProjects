/* This is a short example of MVC.
 * The welcome-file in the deployment descriptor (web.xml) points
 * to this file.  So it is also the starting place for the web
 * application.
 *
 * The servlet is acting as the controller.
 * There are two views - prompt.jsp and result.jsp.
 * It decides between the two by determining if there is a search parameter or not.
 * If there is no parameter, then it uses the prompt.jsp view, as a starting place.
 * If there is a search parameter, then it searches for a picture and uses the result.jsp view.
 * The model is provided by InterestingPictureModel.
 */


package InterestingPicture;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InterestingPictureServlet", urlPatterns = {"/InterestingPictureServlet"})
public class InterestingPictureServlet extends HttpServlet {

    InterestingPictureModel ipm = null;
   //int count=1;
    @Override
    public void init() {
        ipm = new InterestingPictureModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        // get the search parameter if it exists
        String search = request.getParameter("search");
        
        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ua.indexOf("Android") != -1) {
            mobile = true;
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        // see if search parameter is present
        if(search != null) {
                 // use model to do the search and choose the result view
                 ipm.doFlickrSearch(search);
                 request.setAttribute("pictureURL", ipm.interestingPictureSize((mobile) ? "mobile" : "desktop"));
                 request.setAttribute("pictureTag", ipm.getPictureTag());
                 //request.setAttribute("count",count);
                 RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                 view.forward(request, response);
                 
        }
        else {
            // no search parameter so choose the prompt view
            RequestDispatcher view = request.getRequestDispatcher("prompt.jsp");
            view.forward(request, response);
      }
    }
}