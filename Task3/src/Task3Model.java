
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Rohit
 */
public class Task3Model {

    private String pictureTag;
    private String pictureURL;

    public static void main(String[] args) {
        try {
            // Create a URL for the desired page            
                URL url = new URL("http://api-pub.dictionary.com/v001?vid=qsytzj9zyvngy8bgqnq3cd6vmtmou4rlwqfw9ov8jj&q=hello&type=define");
                // Create an HttpURLConnection.  This is useful for setting headers and for getting the
                // path of the resource that is returned (which may be different than the URL above if
                // redirected).
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = null;
                String str;
                while ((str = in.readLine()) != null) {
                    // str is one line of text; readLine() strips the newline character(s)
                    response += str;
                }
                in.close();
                System.out.println(response);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Task3Model.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Task3Model.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
