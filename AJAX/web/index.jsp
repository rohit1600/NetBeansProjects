<%-- 
    Document   : index
    Created on : May 3, 2012, 1:09:42 PM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    <head>
        <script type="text/javascript">
            function loadXMLDoc()
            {
                var xmlhttp;
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }//.... AJAX script goes here ...
                xmlhttp.onreadystatechange=function()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        xmlDoc=xmlhttp.responseXML;
                        txt="";
                        x=xmlDoc.getElementsByTagName("rohit");
                        for (i=0;i<x.length;i++)
                        {
                            txt=txt + x[i].childNodes[0].nodeValue + "<br />";
                        }
                        document.getElementById("myDiv").innerHTML=txt;
                    }
                }
                xmlhttp.open("GET","rohit",true);
                xmlhttp.send();
            }
        </script>
    </head>
    <div id="myDiv"><h2>Let AJAX change this text</h2></div>
    <button type="button" onclick="loadXMLDoc()">Change Content</button>

</body>
</html>