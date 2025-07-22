<%@page import="com.tmf.store.entites.User"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="/css/cart-style.css">
</head>
<body>
	
	
	<%
    	String jsonItems = (String) request.getAttribute("jsonItems");
    	User user = (User) session.getAttribute("user");
    %>
    
    <% 
		if(user == null){%>
			<p style="text-align: center;">User is not logged in</p>
		<%}
	%>
	
	<% if(user != null){ %>
	    <jsp:include page="Header.jsp" />
		<% 	
			List<Item> items = (List<Item>) request.getAttribute("items");
			Gson gson = new Gson();
			String itemsJson = gson.toJson(items);
		%>
	    <div class="item-cart-container">
	    </div>
		
		<div id="checkout"></div>
	    <h4 id="total-amount">Total Amount: 400.00</h4>
		
		<script>
			window.itemsDataJsonCart = <%= itemsJson%>
		</script>
		<script type="text/javascript" src = "/js/cart.js"></script>
	<%}%>
</body>
</html>
