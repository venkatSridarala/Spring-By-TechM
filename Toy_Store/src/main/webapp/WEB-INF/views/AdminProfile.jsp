<%@page import="com.tmf.store.entites.Address"%>
<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="/css/profile-style.css" />
</head>
<body>
<jsp:include page="AdminHeader.jsp" />

<%
    User user = (User) session.getAttribute("user");
%>

<div class="profile-container" id="profile">
    <form method="post" action="/profile/admin">
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

        <!-- Buttons -->
        <div class="actions">
            <button type="button" id="edit" >Edit</button>
            <button type="submit" hidden>Submit</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="/js/adminProfile.js"></script>
</body>
</html>
