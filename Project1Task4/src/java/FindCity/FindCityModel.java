/**
* Author: Rohit Rajagopal
* Last Modified: Feb 8, 2012
*
* This program has a method getCityMap() which takes a city name as input
* getCityMap() generates 2 URL's; returns the pictureURL
* the image URL is stored in the pictureURL
* the link back URL to the main page is stored in the linkBackURL variable
* getLinkBackURL() method returns the linkBackURL 
* cutURL() method to truncate linkBackURL
*/
package FindCity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FindCityModel {
    private String pictureURL;
    private String linkBackURL;
    public String getCityMap(String city){
        String page="";
        try {
            
            // Google search with "Im Feeling Lucky" to land in the urbanrail.net page for that city
            URL url = new URL("http://www.omdbapi.com/?i=&t=independence+day");
            
            // Create an HttpURLConnection.  This is useful for setting headers and for getting the
            // path of the resource that is returned (which may be different than the URL above if
            // redirected).
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            
            //To follow redirects when google redirects to urbanrail.net
            HttpURLConnection.setFollowRedirects(true);
            connection.connect();
            
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            while ((str = in.readLine()) != null) {
                 //str is one line of text; readLine() strips the newline character(s)
                //reads and stores page source redirected page
                page += str + "\n";
            }
            System.out.println(page);
            
            //to use the URL to link back to urbanrail site
            linkBackURL=connection.getURL().toString();
            in.close();
            
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        
    }
    public String getlinkBackURL(){
        return linkBackURL;
    }

    //method first counts number of "/" in the string
    //then truncates string till the last "/"
    public static String cutURL(String x){
        String str=x;
        int y=0;
        char slash='/';
        int numSlash=0;
        for (int i=0;i<str.length();i++){
            if(str.charAt(i) == slash){
                numSlash++;
            }
        }
        for(int i=0;i<numSlash;i++){
            y=str.indexOf("/",y+1);
        }
        str=str.substring(0, y+1);
        return str;
    }
}
