<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>For More Store</title>
<link rel="stylesheet" href="/css/admin-items-style.css">
</head>
<body>
	
	<jsp:include page="AdminHeader.jsp" />
    <!-- Items Grid -->
    <div class="items-container">
        <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            for(Item item : items) {
            	String imageUrl = item.getItemUrlList().get(0).getUrl() != null ? item.getItemUrlList().get(0).getUrl() : "/images/Thar.png";
        %>
            <div class="item-card">
                <!-- Top-right checkbox -->
               <%if(item.isAvailable()){%>
            	   <input type="checkbox" onchange="updateAvailable(<%= item.getItemId() %>)" class="item-checkbox" name="selectedItems" value="<%= item.getItemId() %>" checked />
               <% } else{ %>               
                <input type="checkbox" onchange="updateAvailable(<%= item.getItemId() %>)" class="item-checkbox" name="selectedItems" value="<%= item.getItemId() %>"/>
               <%}%>

                <img src="<%= imageUrl%>" alt="<%= item.getItemName() %> image"/>
                <p><%= item.getItemName() %></p>
                <p>Price: <%= item.getPrice() %></p>
                <p>Available Quantity: <%= item.getAvailableQuantity() %></p>
                <p>Weight: <%= item.getWeight() %></p>
                <p>Category: <%= item.getCategory() %></p>
            </div>
        <%
            }
        %>
    </div>
	<script type="text/javascript" src="/js/adminItems.js"></script>
</body>
</html>
