<%-- 
    Document   : index
    Created on : Jan 19, 2012, 8:16:28 PM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
           <body>
              <% java.util.Date d = new java.util.Date(); %>
              Today is <%= d.toString() %>
           </body>
        </html>