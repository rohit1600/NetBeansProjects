<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<!-- notice that you do not put a semicolon at the end of this special
form of print-->


<html>
    <head>
        <title>Interesting Picture</title>
    </head>
    <body>
       
        <h1>Here is an interesting picture of a <%= session.getAttribute("pictureTag")%></h1><br>
        
        <img <%= session.getAttribute("pictureURL")%>>
        <hr><h2>Your previous picture was of a  <%= session.getAttribute("previousTag")%></h2><br>
        <img <%= session.getAttribute("previousURL")%>>
         <form action="InterestingPictureServletUsingSessions" method="GET">
            <label for="letter">Type another word.</label>
            <input type="text" name="search" value="" /><br>
            <input type="submit" value="Submit" />
            
    </body>
</html>

