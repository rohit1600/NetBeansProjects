<%-- 
    Document   : SearchOther
    Created on : Feb 2, 2012, 1:24:22 AM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1 Task4</title>
    </head>
    <h1>Please enter name of a valid city</h1>
    <body>
        <form action="FindCity" method="GET">
            <table>
                <tr>
                    <td>
                        <h1>Search another city</h1>
                    </td>
                    <td>
                        <input type ="text" name="city">
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                        <input type ="submit" value="Search">
                    </td>
                </tr>
        </form>
        
        
    </body>
</html>
