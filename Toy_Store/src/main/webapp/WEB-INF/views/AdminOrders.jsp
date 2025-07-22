<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.tmf.store.utils.UserOrderItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Orders</title>
<link rel="stylesheet" href="/css/orders-style.css">
</head>
<body>
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
			List<UserOrderItem> orders = (List<UserOrderItem>) request.getAttribute("orders");
	%>
		    <jsp:include page="AdminHeader.jsp" />
			<%
				if(orders.size() == 0){%>
					<h2>No Orders</h2>
				<%}else{%>
					<h2>Your Orders</h2>
		
				    <div class="orders-container">
				    <%
				    	for(UserOrderItem order:orders){ %>
				    		<div class="order-card">
				            	<p class="order-date">Ordered on: <%= formatDateTime(order.getOrderedOn()) %></p>
				            	<p class="order-date">Order Id: <%= order.getOrderId()%></p>
				            	<p class="order-date">User Id: <%= order.getUserId() %></p>
				            	<p class="order-date">User name: <%= order.getFullName() %></p>
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
			            					<select name="status" onchange="updateStatus(event,<%= order.getOrderedItemId()%>)" required>
			            						<option value="" disabled selected>Update status</option>
			            						<option value="cancelled">Cancel</option>
			            						<option value="ofd">Out for Delivery</option>
			            					</select>
			            				</div>
			            			<% }else{%>
			            				<div class="order-status"><%= order.getStatus().equals("ofd") ? "Out for Delivery" : order.getStatus() %></div>
			            			<%}
			            		%>
				            	
				        	</div>
				    	<%}
				    %>
				        
				    </div>
				<%}
			%>	
	<script type="text/javascript" src="/js/adminOrders.js"></script>   
</body>
</html>
