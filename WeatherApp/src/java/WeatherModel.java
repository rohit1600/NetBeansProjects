

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherModel {
	int currentTemp, maxTemp, minTemp;
	public int getCurrentTemp(){
		return currentTemp;
	}
	public int getMaxTemp(){
		return maxTemp;
	}
	public int getMinTemp(){
		return minTemp;
	}
        public static void main(String[] args){
            WeatherModel wm=new WeatherModel();
            wm.doSearch("a;lisjd;las");
            System.out.println(wm.getCurrentTemp());
        }

	
	public void doSearch(String location) {
		String page="";
        try {
        	System.out.println("location " +location);
            
            // Google search with "Im Feeling Lucky" to land in the urbanrail.net page for that city
            URL url = new URL("http://www.google.com/search?q="+location.replaceAll(" ", "+") +"+site:weather.com&btnI=Im+Feeling+Lucky");
            
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
            //System.out.println(page);
            
            //to use the URL to link back to urbanrail site
            in.close();
            
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        System.out.println(page);
        try {
            int startfarm=0;
            int endfarm=0;
            
            //extract the image name from the first image tag in the source code
            //including width and height of image
            startfarm=page.toUpperCase().indexOf("REALTEMP");
            startfarm=page.indexOf("\"", startfarm);
            endfarm=page.toUpperCase().indexOf("\"",startfarm+1);
            System.out.println(page.substring(startfarm+1, endfarm));
            currentTemp=Integer.parseInt(page.substring(startfarm+1, endfarm));
            //cutURL to form the initial part of the image URL by truncating link back URL
            //startfarm=page.indexOf("",endfarm+1)
            
        } catch (Exception ex) {
        }
        //return pictureURL to calling program
        
   }

}
