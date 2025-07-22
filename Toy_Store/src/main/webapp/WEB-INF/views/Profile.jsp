<%@page import="com.tmf.store.entites.Address"%>
<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel="stylesheet" href="/css/profile-style.css" />
</head>
<body>
<jsp:include page="Header.jsp" />

<%
    User user = (User) session.getAttribute("user");
    List<Address> addressList = (List<Address>) request.getAttribute("addresses");
%>

<div class="profile-container" id="profile">
    <form method="post" action="/profile">
        <!-- User Details -->
        <section class="section">
            <h2>User Details</h2>
            <div class="field-group">
                <div class="field">
                    <label>Firstname:</label>
                    <div class="readonly-value"><%= user.getFirstName() %></div>
                    <input type="text" name="firstname" value="<%= user.getFirstName() %>" required />
                </div>
                <div class="field">
                    <label>Lastname:</label>
                    <div class="readonly-value"><%= user.getLastName() %></div>
                    <input type="text" name="lastname" value="<%= user.getLastName() %>" required />
                </div>
                <div class="field">
                    <label>Email:</label>
                    <div class="readonly-value"><%= user.getEmail() %></div>
                    <input type="email" name="email" value="<%= user.getEmail() %>" required />
                </div>
                <div class="field">
                    <label>Phone Number:</label>
                    <div class="readonly-value"><%= user.getPhno() %></div>
                    <input type="tel" name="phno" value="<%= user.getPhno() %>" required />
                </div>
            </div>
        </section>

        <!-- Address Details -->
        <section class="section" id="address-section">
            <h2>Address Details</h2>
            <% for (Address address : addressList) { %>
            <div class="field-group address-field">
                <div class="field">
                    <label>Door No:</label>
                    <div class="readonly-value"><%= address.getDoorNo() %></div>
                    <input type="text" name="doorno" value="<%= address.getDoorNo() %>" required />
                </div>
                <div class="field">
                    <label>Area:</label>
                    <div class="readonly-value"><%= address.getArea() %></div>
                    <input type="text" name="area" value="<%= address.getArea() %>" required />
                </div>
                <div class="field">
                    <label>City:</label>
                    <div class="readonly-value"><%= address.getCity() %></div>
                    <input type="text" name="city" value="<%= address.getCity() %>" required />
                </div>
                <div class="field">
                    <label>Pincode:</label>
                    <div class="readonly-value"><%= address.getPinCode() %></div>
                    <input type="number" name="pincode" value="<%= address.getPinCode() %>" required />
                </div>
                <div class="field">
                    <label>State:</label>
                    <div class="readonly-value"><%= address.getState() %></div>
                    <input type="text" name="state" value="<%= address.getState() %>" required />
                </div>
            </div>
            <input type="hidden" name="addressId" value="<%= address.getAddressId()%>">
            <% } %>
        </section>

        <!-- Buttons -->
        <div class="actions">
        	<button id="new-address" type="button">New address</button> 
        	<!--  <button id="new-address-submit" hidden type="button">Submit.</button>  -->
            <button type="button" id="edit" >Edit</button>
            <button type="submit" hidden>Submit</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="/js/profile.js"></script>
</body>
</html>
