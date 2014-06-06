<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Give me a keyword, and I'll give you an interesting picture.</p>
        <form action="InterestingPictureServlet" method="GET">
            <label for="letter">Type the word.</label>
            <input type="text" name="search" value="" /><br>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>