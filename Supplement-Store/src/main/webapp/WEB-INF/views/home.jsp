<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Supplements Store - Home</title>
    <link rel="stylesheet" href="/static/css/home-style.css">
</head>
<body>

    <header>
        <div class="container">
            <h1>Supplements Store</h1>
            <nav>
                <a href="/home">Home</a>
                <a href="/products">Products</a>
                <a href="/cart">Cart</a>
                <a href="/auth/login">Login</a>
                <a href="/auth/signup">Register</a>
            </nav>
            <form class="search-box">
                <input type="text" placeholder="Search for supplements...">
                <button type="submit">Search</button>
            </form>
        </div>
    </header>

    <main>
        <section class="hero">
            <h2>Welcome to Supplements Store</h2>
            <p>Your trusted source for quality health supplements.</p>
        </section>
    </main>
    
    <h2>Available Supplements</h2>
    
<div class="product-list">
    <c:forEach var="product" items="${products}">
        <div class="product-card">
<img src="${pageContext.request.contextPath}${product.imageUrl}" alt="${product.name}" width="200" height="200"/>
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p><strong>₹ ${product.price}</strong></p>
            <form action="/cart/add" method="post">
    <input type="hidden" name="productId" value="${product.id}" />
    <input type="hidden" name="userId" value="${sessionScope.user.id}" />
    <button type="submit">Add to Cart</button>
</form>

        </div>
    </c:forEach>
</div>

    <footer>
        <p>© 2025 Supplements Store. All rights reserved.</p>
    </footer>

</body>
</html>
