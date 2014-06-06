/* This is a short example of MVC.
 * The welcome-file in the deployment descriptor (web.xml) points
 * to this file.  So it is also the starting place for the web
 * application.
 *
 * The servlet is acting as the controller.
 * There are two views - prompt.jsp and result.jsp.
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "InterestingPictureServletUsingSessions", urlPatterns = {"/InterestingPictureServletUsingSessions"})
public class InterestingPictureServletUsingSessions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InterestingPictureModel gipm = new InterestingPictureModel();

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
        String search = request.getParameter("search");
        if (search != null) {
            // use model to do the search and choose the result view
            gipm.doFlickrSearch(search);
            HttpSession session = request.getSession();
            RequestDispatcher view;
            if (session.getAttribute("pictureURL") == null) { // if there was no previous search
                view = request.getRequestDispatcher("resultSessions.jsp");                
                request.setAttribute("pictureURL", gipm.interestingPictureSize((mobile) ? "mobile" : "desktop"));
                request.setAttribute("pictureTag", gipm.getPictureTag());

            } else {
                session.setAttribute("previousURL", session.getAttribute("pictureURL"));
                session.setAttribute("previousTag", session.getAttribute("pictureTag"));
                view = request.getRequestDispatcher("twoResults.jsp");
            }
            session.setAttribute("pictureURL", gipm.interestingPictureSize((mobile) ? "mobile" : "desktop"));
            session.setAttribute("pictureTag", gipm.getPictureTag());
            view.forward(request, response);
        } else {
            // no search parameter so choose the prompt view
            RequestDispatcher view = request.getRequestDispatcher("promptSessions.jsp");
            view.forward(request, response);

        }
    }
}