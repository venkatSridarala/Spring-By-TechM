<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Catalog</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h2>Available Products</h2>
<div class="product-grid">
    <c:forEach items="${products}" var="product">
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}" height="120"/><br/>
            <strong>${product.name}</strong><br/>
            <p>${product.description}</p>
            <p><b>₹${product.price}</b></p>
            <p>Stock: ${product.quantity}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>