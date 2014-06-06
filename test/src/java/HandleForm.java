//	 HandleForm.java 
	//	 An introductory servlet

	import java.io.IOException;
	import java.io.PrintWriter;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	public class HandleForm extends HttpServlet{
	   
	   public void doGet(HttpServletRequest req,
	                     HttpServletResponse response)
	                     throws ServletException,
	                     IOException     {

	          String personName = req.getParameter("personName");

	          response.setContentType("text/html");

	          PrintWriter out = response.getWriter();

	          String docType = "<!DOCTYPE HTML PUBLIC \"//W3C//DTD HTML 4.0 ";
	                 docType += "Transitional//EN\">\n";
	                 
	                
	          out.println(docType + 
	                      "<HTML>\n" +
	                      "<HEAD><TITLE>Hi" + personName + "</TITLE></HEAD>\n" +
	                      "<BODY>\n" +
	                      "<H1> Hi"+ personName + "</H1>\n" + 
	                      "</BODY></HTML>");
	   }

	} 
