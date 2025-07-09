<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin-Dashboard</title>
</head>
<body>
<h2>Admin Dashboard</h2>

<h3>All Products</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>₹${p.price}</td>
            <td>
                <form method="post" action="/admin/deleteProduct">
                    <input type="hidden" name="id" value="${p.id}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>All Orders</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Status</th>
        <th>Update</th>
    </tr>
    <c:forEach var="o" items="${orders}">
        <tr>
            <td>${o.id}</td>
            <td>${o.user.name}</td>
            <td>${o.status}</td>
            <td>
                <form method="post" action="/admin/updateOrderStatus">
                    <input type="hidden" name="id" value="${o.id}" />
                    <select name="status">
                        <option>Placed</option>
                        <option>Shipped</option>
                        <option>Delivered</option>
                    </select>
                    <button type="submit">Update</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>