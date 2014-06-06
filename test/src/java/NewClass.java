
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohit
 */
public class NewClass {

    public static void main(String[] args) throws MalformedURLException {
        try {
            String page = null;
            String str = null;
            int startfarm = 0, endfarm = 0;

            URL url = new URL("http://localhost:8080/P5T3WebServiceWebApplicationClientProject/P5T3WebServiceClientServlet?hotel=1&num_rooms=0&car=1&num_cars=0&plane=1&num_seats=0&submit=Submit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                page += str;
            }
            System.out.println(page);
            in.close();
            startfarm = page.indexOf("<p>");
            endfarm = page.indexOf("</p>");
            System.out.println(page.substring(startfarm+3, endfarm));
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
