<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="/static/css/cart-style.css">
</head>
<body>
<h2>Your Shopping Cart</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
        <th>Action</th>
    </tr>
    <c:forEach var="item" items="${cartItems}">
        <tr>
            <td>${item.product.name}</td>
            <td>${item.quantity}</td>
            <td>₹ ${item.product.price}</td>
            <td>₹ ${item.product.price * item.quantity}</td>
            <td>
                <form method="post" action="/cart/remove">
                    <input type="hidden" name="cartItemId" value="${item.id}" />
                    <input type="submit" value="Remove" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
