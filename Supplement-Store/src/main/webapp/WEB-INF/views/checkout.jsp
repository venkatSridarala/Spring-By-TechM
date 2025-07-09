<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<h2>Enter Shipping Address</h2>
<form method="post" action="/checkout">
    Street: <input type="text" name="street" required><br>
    City: <input type="text" name="city" required><br>
    State: <input type="text" name="state" required><br>
    ZIP: <input type="text" name="zip" required><br><br>
    <button type="submit">Place Order</button>
</form>
</body>
</html>

// --- View: order_tracking.jsp ---
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Tracking</title>
</head>
<body>
<h2>Your order has been placed successfully!</h2>
<p>Your Order ID: ${orderId}</p>
<a href="/products">Back to catalog</a>
</body>
</html>