<%-- 
    Document   : index
    Created on : Jan 28, 2012, 11:35:13 PM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1 Task1</title>
    </head>
    <h1>Project1Task1</h1>
    <body>
        <form action="ComputeHashes" method="GET">
            <table>
                <tr>
                    <td>Enter String Here</td>
                    <td> <input type="text" name="text"></td>
                <tr>
                    <td>Select Hash Function</td>
                    <td><input type="radio" name="group1" value="MD5" checked> MD5 <br></td>
                <tr><td></td>
                    <td>  <input type="radio" name="group1" value="SHA-1"> SHA-1</td>
                <br>
                <tr><td</td><td><input type="submit" value="Submit"></td>
                
        </form>
    </body>
</html>
