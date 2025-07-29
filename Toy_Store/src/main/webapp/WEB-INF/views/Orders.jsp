<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.tmf.store.utils.UserOrderItem"%>
<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Orders</title>
<link rel="stylesheet" href="/css/orders-style.css">
</head>
<body>

	
	<%
    	String jsonItems = (String) request.getAttribute("jsonItems");
    	User user = (User) session.getAttribute("user");
    %>
    <%!
    	public static String formatDateTime(LocalDateTime orderedOn){
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    		String formattedDateTime = orderedOn.format(formatter);
    		return formattedDateTime;
    	}
    
    	public static String formatPrice(double price){
    		String temp = String.valueOf(price);
    		if(temp.contains(".") == false) return (temp + ".00");
    		return temp;
    	}
    %>
    <% 
		if(user == null){%>
			<p style="text-align: center;">User is not logged in</p>
		<%}
	%>
	
	<% 
		if(user != null){ 
			List<UserOrderItem> orders = (List<UserOrderItem>) request.getAttribute("orders");
	%>
		    <jsp:include page="Header.jsp" />
			<%
				if(orders.size() == 0){%>
					<h2>No Orders</h2>
				<%}else{%>
					<h2>My Orders</h2>
		
				    <div class="orders-container">
				    <%
				    	for(UserOrderItem order:orders){ %>
				    		<div class="order-card">
				            	<p class="order-date">Ordered on: <%= formatDateTime(order.getOrderedOn()) %></p>
				            	<div class="order-details">
				                	<img src="/images/apple.jpg" alt="<%= order.getItemName()%>" />
				                	<div class="product-info">
				                    	<p><strong>Name:</strong> <%= order.getItemName()%></p>
				                    	<p><strong>Price:</strong> <%= formatPrice(order.getPrice()) %></p>
				                    	<p><strong>Quantity:</strong> <%= order.getQuantity() %></p>
				                	</div>
			            		</div>
			            		<%
			            			if(order.getStatus().equals("ordered")){%>
			            				<div class="order-status">			            					
			            					<a onclick="cancelItem(<%= order.getOrderedItemId()%>)">Cancel</a>
			            				</div>
			            			<% }else{%>
			            				<div class="order-status"><%= order.getStatus().equals("ofd") ? "Out for Delivery" : order.getStatus() %></div>
			            			<%}
			            		%>
				            	
				        	</div>
				    	<%}
				    %>
				        
				
				   <!--  <div class="order-card">
				            <p class="order-date">Ordered on: 20 June 2025</p>
				            <div class="order-details">
				                <img src="/images/apple.jpg" alt="Apple" />
				                <div class="product-info">
				                    <p><strong>Name:</strong> Apple</p>
				                    <p><strong>Price:</strong> 250.00</p>
				                    <p><strong>Quantity:</strong> 1</p>
				                </div>
				            </div>
				            <div class="order-status">Delivered</div>
				        </div>  -->
				    </div>
				<%}
			%>	    
	<%}%>
	<script type="text/javascript" src="/js/order.js"></script>
</body>
</html>
