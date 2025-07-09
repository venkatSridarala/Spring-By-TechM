<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value='/static/css/signup-style.css'/>" />
</head>
<body>
    <h2>Register</h2>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <form method="POST" action="/auth/signup">
        <input type="text" name="username" placeholder="Username" required/><br/><br/>
        <input type="email" name="email" placeholder="Email" required/><br/><br/>
        <input type="password" name="password" placeholder="Password" required/><br/><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
