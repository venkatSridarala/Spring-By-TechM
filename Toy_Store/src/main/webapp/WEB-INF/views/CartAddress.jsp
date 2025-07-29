<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.Address"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Address> addressList = (List<Address>) request.getAttribute("addresses");
	%>
	<div>
	<section class="section" id="address-section">
	<% for(Address address : addressList){ %>
		<label>
		<input type="radio" name="addressId" value="<%= address.getAddressId()%>">
 		<div class="field-group" >
 			<div class="field">
               <label>Door No:</label>
               <div class="readonly-value"><%= address.getDoorNo() %></div>
           </div>
           <div class="field">
               <label>Area:</label>
               <div class="readonly-value"><%= address.getArea() %></div>
           </div>
           <div class="field">
               <label>City:</label>
               <div class="readonly-value"><%= address.getCity() %></div>           
           </div>
           <div class="field">
               <label>Pincode:</label>
               <div class="readonly-value"><%= address.getPinCode() %></div>        
           </div>
           <div class="field">
               <label>State:</label>
               <div class="readonly-value"><%= address.getState() %></div>
           </div>
 		</div>
 		</label>
	<% }%>
		<button onclick="selectAddress()" id="buy">Buy</button>
	</section>
	</div>
	<script type="text/javascript" src="/js/cartAddress.js"></script>
</body>
</html>