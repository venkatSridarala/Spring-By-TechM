<%@page import="com.tmf.store.entites.ItemURL"%>
<%@page import="java.util.List"%>
<%@page import="com.tmf.store.entites.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
<style>
    body {
        font-family: 'Segoe UI', sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 20px;
    }

    .product-container {
        max-width: 700px;
        margin: 0 auto;
        background: #ffffff;
        border-radius: 12px;
        padding: 25px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    .carousel {
        position: relative;
        width: 100%;
        height: 350px;
        overflow: hidden;
        border-radius: 10px;
        margin-bottom: 10px;
        background-color: #fff;
    }

    .carousel img {
        width: 100%;
        height: 100%;
        object-fit: contain;
        position: absolute;
        opacity: 0;
        transition: opacity 0.4s ease-in-out;
    }

    .carousel img.active {
        opacity: 1;
        position: relative;
    }

    .carousel-arrow {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        background: rgba(0, 0, 0, 0.5);
        color: white;
        font-size: 22px;
        border: none;
        padding: 10px;
        border-radius: 50%;
        cursor: pointer;
        z-index: 5;
    }

    .carousel-arrow.left {
        left: 15px;
    }

    .carousel-arrow.right {
        right: 15px;
    }

    .dots {
        display: flex;
        justify-content: center;
        margin-top: 10px;
    }

    .dot {
        width: 10px;
        height: 10px;
        background-color: #ccc;
        border-radius: 50%;
        margin: 0 4px;
        transition: background-color 0.3s;
        cursor: pointer;
    }

    .dot.active {
        background-color: #00bcd4;
    }

    .product-details {
        margin-top: 20px;
    }

    .product-details h3 {
        font-size: 24px;
        margin-bottom: 10px;
        color: #222;
    }

    .product-details p {
        font-size: 15px;
        margin: 4px 0;
        color: #555;
    }

    .product-details p strong {
        color: #222;
        font-weight: 600;
    }

    .low-stock {
        color: red;
        font-weight: bold;
        margin-top: 12px;
    }
    
    .header {
        background-color: #00bcd4;
        padding: 15px 30px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: white;
    }

    .header a {
        color: white;
        text-decoration: none;
        font-weight: 500;
        margin: 0 15px;
    }

    .header a:hover {
        text-decoration: underline;
    }
    

</style>
</head>
<body>

<%
    Item item = (Item) request.getAttribute("item");
    String itemName = item.getItemName();
    List<ItemURL> itemImages = item.getItemUrlList();
%>

<%! 
    public static String formatPrice(double price){
        String temp = String.valueOf(price);
        if(!temp.contains(".")) return (temp + ".00");
        if(temp.endsWith(".0")) return temp + "0";
        return temp;
    }
%>

<jsp:include page="Header.jsp" />

<div class="product-container">
    <!-- Carousel -->
    <div class="carousel" id="carousel">
        <% int index = 0;
           for (ItemURL url : itemImages) {
               if (index >= 3) break;
        %>
            <img src="<%= url.getUrl() %>" alt="Image <%= index+1 %>" class="<%= (index == 0) ? "active" : "" %>"/>
        <% index++; } %>
        <button class="carousel-arrow left" onclick="prevSlide()">&#60;</button>
        <button class="carousel-arrow right" onclick="nextSlide()">&#62;</button>
    </div>

    <!-- Dots -->
    <div class="dots" id="dots"></div>

    <!-- Product Details -->
    <div class="product-details">
        <h3><%= itemName %></h3>
        <p><strong>Price:</strong> <%= formatPrice(item.getPrice()) %></p>
        <p><strong>Description:</strong> <%= item.getDescription() %></p>
        <p><strong>Weight:</strong> <%= item.getWeight() %> kg</p>
        <p><strong>Category:</strong> <%= item.getCategory() %></p>
        <% if(item.getAvailableQuantity() < 4) { %>
            <p class="low-stock">Only <%= item.getAvailableQuantity() %> left. Hurry up!!!</p>
        <% } %>
    </div>
</div>

<script>
    const images = document.querySelectorAll("#carousel img");
    const dotsContainer = document.getElementById("dots");
    let currentIndex = 0;

    function showSlide(index) {
        images.forEach((img, i) => {
            img.classList.toggle("active", i === index);
            dotsContainer.children[i].classList.toggle("active", i === index);
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % images.length;
        showSlide(currentIndex);
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        showSlide(currentIndex);
    }

    // Init Dots
    images.forEach((img, i) => {
        const dot = document.createElement("span");
        dot.classList.add("dot");
        if(i === 0) dot.classList.add("active");
        dot.addEventListener("click", () => {
            currentIndex = i;
            showSlide(currentIndex);
        });
        dotsContainer.appendChild(dot);
    });
</script>

</body>
</html>
