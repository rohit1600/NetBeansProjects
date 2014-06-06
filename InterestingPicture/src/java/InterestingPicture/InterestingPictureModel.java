    
package InterestingPicture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class InterestingPictureModel {
    private String pictureTag;
    private String pictureURL;

    public void doFlickrSearch(String search) {
         pictureTag = search;
         String response = "";
        try {
            // Create a URL for the desired page            
            URL url = new URL("http://www.flickr.com/photos/tags/"+search+"/interesting/");
            // Create an HttpURLConnection.  This is useful for setting headers and for getting the
            // path of the resource that is returned (which may be different than the URL above if
            // redirected).
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                response += str;
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        // find the picture URL to scrape
        int startfarm = response.indexOf("src=\"http://farm");

        int endfarm = response.indexOf("\"", startfarm+5); // only start looking after the quote before http
        pictureURL = response.substring(startfarm, endfarm+1); // +1 to include the quote
    }
    
     public String interestingPictureSize(String picsize) {
        int finalDot = pictureURL.lastIndexOf(".");
        String sizeLetter = (picsize.equals("mobile")) ? "m" : "z";
        return (pictureURL.substring(0, finalDot-1)+sizeLetter+pictureURL.substring(finalDot));
    }

    public String getPictureTag() {
        return (pictureTag);
    }
}