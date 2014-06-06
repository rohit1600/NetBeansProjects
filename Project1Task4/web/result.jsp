<%-- 
    Document   : result
    Created on : Feb 2, 2012, 2:06:40 AM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>City Search Result</title>
    </head>
    <body>
        <h1><%= request.getAttribute("error")%></h1>
        <h1> <%= request.getAttribute("message") %></h1>
        <a href="<%= request.getAttribute("linkBackURL")%>">
            <img src="<%=request.getAttribute("cityURL")%> >
        </a>
        <br>
        <h1>Search Another City </h1>
        <form action="FindCity" method="GET">
            <input type="text" name="city"><input type="submit" name="submit">
            
        </form>
    </body>
</html>
