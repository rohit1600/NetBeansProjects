<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<!-- notice that you do not put a semicolon at the end of this special
form of print-->


<html>
    <head>
        <title>Interesting Picture</title>
    </head>
    <body>
         <p>This picture is number <%= session.getAttribute("x")%> in this session.</p>
        <h1>Here is an interesting picture of a <%= session.getAttribute("pictureTag")%></h1><br>
        
        <img <%= session.getAttribute("pictureURL")%>><br><br>
         <form action="InterestingPictureServletCount" method="GET">
            <label for="letter">Type another word.</label>
            <input type="text" name="search" value="" /><br>
            <input type="submit" value="Submit" />
        </form>
         
    </body>
</html>
