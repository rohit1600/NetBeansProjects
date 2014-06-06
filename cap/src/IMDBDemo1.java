/*********************************************************************************
  Program to demonstrate how to use IMDB api to get details about any movie
  Developed by : Malhar Vora
  Developed on : 25-02-2011
  Development Status : Completed and tested
  Email              : vbmade2000@gmail.com
  WebSite            : http://malhar2010.blogspot.com
**********************************************************************************/

import java.io.*;
import java.net.*;
import java.util.*;

import org.json.*;
public class IMDBDemo1{
 
 public static String IMDBDemoGetResult (String str){
   //http://www.omdbapi.com/?i=&t=
  URL url = null;
  Scanner sc = null;
  String apiurl="http://www.omdbapi.com/?i=&t=";
  String moviename=null;  
  String dataurl=null;
  String retdata=null;
  InputStream is = null;
  DataInputStream dis = null;
  String result=null;
  

  try{
    
   //Getting movie name from user
   //sc = new Scanner(System.in);
   moviename=str;
   
   //Check if user has inputted nothing or blank
   if(moviename==null || moviename.equals("")){
    result="No movie found";
    return result;
   }
   
   //Remove unwanted space from moviename yb trimming it
   moviename=moviename.trim();
   
   //Replacing white spaces with + sign as white spaces are not allowed in IMDB api
   moviename=moviename.replace(" ","+");
   
   //Forming a complete url ready to send (type parameter can be JSON also)
   dataurl=apiurl+moviename;
      
   //System.out.println("Getting data from service");
   //System.out.println("########################################");
   
   url = new URL(dataurl);   
   
   is = url.openStream();
   dis  = new DataInputStream(is);
   
   String details[];
   //Reading data from url
   while((retdata = dis.readLine())!=null){
    //Indicates that movie does not exist in IMDB databse
    if(retdata.equals("error|Film not found")){
     result="No such movie found";
     return result;
     
    }
    
    //Replacing | character with # character for spliting
    retdata=retdata.replace("|","#");
    
    //Splitting up string by # character and storing output in details array
    details=retdata.split("#");
    for (int i=0;i<details.length;i++)
    {
    //details[0] contains name of detail. e.g title,genre etc
    result=result+details[i]+"\n";
    }
    
    //details[1] contains value of detail. e.g The Cave
    return details[0].toUpperCase();
   }  
      
  }  
  catch(Exception e){
   System.out.println(e);
  }
  finally{
   try{
   
    if(dis!=null){
     dis.close();
    }
    
    if(is!=null){
     is.close();
    }
    
    if(sc!=null){
     sc.close();
    }
   }
   catch(Exception e2){
    ;
   }
  }
   
  return result;   
 }
public static void main(String[] args) throws JSONException {
	String moviename;
	
	try{  
	      
		   //Getting movie name from user  
		   ArrayList<String> movienames=readTXTFile("Title.csv");
		   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
		           new FileOutputStream("C:\\Users\\Rohit\\Desktop\\movieresults6.csv"), "UTF-8"));
		   Iterator<String> iterator=movienames.iterator();
		   bw.write("ImdbID,Title,Year,Released,Rated,Genre,Runtime,Director,Writer,Actors,Plot,Posters,Votes,Rating");
		   bw.newLine();
		   while(iterator.hasNext()){
			   moviename=iterator.next();
			   //Check if user has inputted nothing or blank  
			   String a=IMDBDemoGetResult(moviename);
				JSONObject json=new JSONObject(a);
				System.out.println(a);
				if(json.getString("RESPONSE").equals("TRUE")==false){
					System.out.println("movie not found,");
					bw.newLine();
				continue;
				}
				String title=json.getString("TITLE");
				String year=json.getString("YEAR");
				String rated=json.getString("RATED");
				String released=json.getString("RELEASED");
				String runtime=json.getString("RUNTIME");
				String genre=json.getString("GENRE").replace(",", "||");;
				String director=json.getString("DIRECTOR").replace(",", "||");
				String writer=json.getString("WRITER").replace(",", "||");
				String actors=json.getString("ACTORS").replace(",", "||");
				String plot=json.getString("PLOT").replace(",", "||");
				String poster=json.getString("POSTER");
				String imdbid=json.getString("IMDBID");
				String imdbrating=json.getString("IMDBRATING");
				String imdbvotes=json.getString("IMDBVOTES").replace(",", "/");
				System.out.println(imdbid);
				System.out.println(title);
				bw.write(imdbid+",");
				bw.write(title+",");
				bw.write(year+",");
				bw.write(released+",");
				bw.write(rated+",");
				bw.write(genre+",");
				bw.write(runtime+",");
				bw.write(director+",");
				bw.write(writer+",");
				bw.write(actors+",");
				bw.write(plot+",");
				bw.write(poster+",");
				bw.write(imdbvotes+",");
				bw.write(imdbrating);
			   	bw.newLine();

		  }   
		   bw.flush();
		   bw.close();
	}
		  catch(Exception e){  
		   System.out.println(e);  
		  }  
		
}
public static ArrayList<String> readTXTFile(String csvFileName) throws Exception {

	  BufferedReader stream = new BufferedReader(
	                          new InputStreamReader(
	                          new FileInputStream(csvFileName)));

	  ArrayList<String> moviename = new ArrayList<String>();

	  String line;
	  while ((line = stream.readLine()) != null) {

	    moviename.add(line);

	  }

	  return moviename;
	}
}

