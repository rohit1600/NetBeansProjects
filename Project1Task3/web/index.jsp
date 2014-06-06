<%-- 
    Document   : index
    Created on : Jan 29, 2012, 4:30:06 PM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1 Task3</title>
    </head>
    <body>
        <h1>Palindrome Check!</h1>
        <form action="Palin" method="GET">
            <table>
                <tr>
                    <td>Enter String here
                    <td><input type="text" name="pal"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Palindrome Check"</td>
            </table>
        </form>
    </body>
</html>
