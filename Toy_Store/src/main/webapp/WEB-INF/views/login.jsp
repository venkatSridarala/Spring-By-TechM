<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store</title>
<link href="/css/login-style.css" rel="stylesheet"> 
</head>
<body>
	<%
		String errorMessage = (String) request.getAttribute("error");
	%>
    <div class="login-container">
        <h1>Login</h1>
        <% if(errorMessage != null){%>
			<p class="error"><%= errorMessage%></p>
		<%}%>
        <form method="post" id="form" action="/login/auth">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required="required" value="client1@fitstore.com" />
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="pass1234" required="required"/>
            
            <input type="submit" value="Login"/>
        </form>
        <div class="anchor-container">
        	<button id="admin" onclick="adminLogin()">Admin Login</button>
        	<button id="user" onclick="userLogin()" hidden>User Login</button>
        </div>
        <div><a href="/register">Sign up?</a></div>
    </div>
    <script type="text/javascript" src="/js/login.js"></script>
</body>
</html>
