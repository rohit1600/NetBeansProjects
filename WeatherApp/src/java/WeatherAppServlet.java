
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class WeatherAppServlet extends HttpServlet {

    WeatherModel wm = null;

    @Override
    public void init() {
        wm = new WeatherModel();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
            //String location=(String) req.getParameter("location");
            String location = "Pittsburgh";
            System.out.println("location " + location);
            wm.doSearch(location);
            resp.setContentType("text/xml");
            PrintWriter out = resp.getWriter();
            out.println("<?xml version=\"1.0\"?>");
            out.println("<currentTemp>");
            out.println(wm.getCurrentTemp());
            out.println("</currentTemp>");
            out.println("<maxTemp>");
            out.println(wm.getMaxTemp());
            out.println("</maxTemp>");
            out.println("<minTemp>");
            out.println(wm.getMinTemp());
            out.println("</minTemp>");
    }
}
