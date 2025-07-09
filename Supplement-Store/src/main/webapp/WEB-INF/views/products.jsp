<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="/static/css/products-style.css">
</head>
<body>
<h2>All Products</h2>
<div class="product-list">
    <c:forEach var="product" items="${products}">
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}" />
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p><strong>₹ ${product.price}</strong></p>
            
<img src="${pageContext.request.contextPath}${product.imageUrl}" alt="${product.name}" width="200" height="200"/>
            <form action="/cart/add" method="post">
    <input type="hidden" name="productId" value="${product.id}" />
    <input type="hidden" name="userId" value="${sessionScope.user.id}" />
    <button type="submit">Add to Cart</button>
</form>

        </div>
    </c:forEach>
</div>
</body>
</html>
