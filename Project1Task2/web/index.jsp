<%-- 
    Document   : index
    Created on : Jan 29, 2012, 2:36:09 AM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1 Task2</title>
    </head>
    <body>
        <form action="BigCalc" method="GET">
            <table>
                <tr>
                    <td>Enter Integer X</td>
                    <td> <input type="text" name="x"> </td>
                <tr>
                    <td>Enter Integer Y</td>
                    <td><input type="text" name="y"</td>
                <tr>
                    <td>Select Operation</td>
                    <td><select name="operation">
                            <option value="add">Add (X + Y)</option>
                            <option value="multiply">Multiply (X * Y)</option>
                            <option value="relativelyPrime">Relatively Prime?</option>
                            <option value="mod">Modulus (X % Y)</option>
                            <option value="modInverse">Modulus Inverse</option>
                            <option value="power">Power (X ^ Y)</option>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit">
        </form>
    </body>
</html>
