<%@ page import="java.util.*" %>
<%@ page import="developerworks.ajax.store.*" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" language="javascript" src="ajax1.js"></script>
<script type="text/javascript" language="javascript" src="cart.js"></script>
<LINK REL=StyleSheet HREF="newcss.css" TYPE="text/css" MEDIA=screen>
    
</head>
<body>
<div style="float: left; width: 500px">
<h2>Catalog</h2>
<table id="table" border="1">
  <thead><th>Name</th><th>Description</th><th>Price</th><th></th></thead>
  <tbody>
  <%
    for (Iterator<Item> I = new Catalog().getAllItems().iterator() ; I.hasNext() ; ) {
      Item item = I.next();
      Item item2=null;
      if(I.hasNext())
          item2=I.next();
                   
  %>
    <tr><td><%= item.getName() %></td><td><%= item.getDescription() %></td><td><%= item.getFormattedPrice() %></td><td><button class="green" onclick="addToCart('<%= item.getCode() %>')">Add to Cart</button></td></tr>
    
    <tr class="alt"><td><%= item2.getName() %></td><td><%= item2.getDescription() %></td><td><%= item2.getFormattedPrice() %></td><td><button class="green" onclick="addToCart('<%= item2.getCode() %>')">Add to Cart</button></td></tr>
    <% } %>
  </tbody>
</table>
<div class="boxed">
<h2>Cart Contents</h2>
<ul id="contents">
</ul>
Total cost: <span id="total">$0.00</span>
</div>
</body>
</html>
