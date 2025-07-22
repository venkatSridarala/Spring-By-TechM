<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Toy's Store</title>
  <link href="/css/items-style.css" rel="stylesheet">
</head>
<body>

  <c:if test="${empty user}">
    <p style="text-align:center;">User is not logged in</p>
  </c:if>

  <c:if test="${not empty user}">
    <jsp:include page="Header.jsp"/>

    <!-- Search and Category Bar -->
    <div class="search-container">
      <div class="input-container">
        <input type="text" placeholder="Search" id="search"/>
        <img src="/images/search-logo.png" alt="Search logo"/>
      </div>
		<div>
		  <select name="category" id="category">
		    <option value="all">All Toys</option>
		    <option value="vehicles">Remote Control Cars & Vehicles</option>
		    <option value="drones">Drones & Helicopters</option>
		    <option value="building-sets">Building Sets & Blocks</option>
		    <option value="dolls">Dolls & Figures</option>
		    <option value="tracks">Track Builder Kits</option>
		    <option value="blasters">Blasters & Nerf Toys</option>
		    <option value="puzzles">Puzzles & Brain Teasers</option>
		    <option value="electronics">Electronic Toys & Games</option>
		    <option value="plush">Plush & Stuffed Animals</option>
		    <option value="music">Musical Toys & Instruments</option>
		    <option value="arts-crafts">Arts & Crafts Kits</option>
		    <option value="card-games">Card & Board Games</option>
		    <option value="roleplay">Role‑Play & Pretend Play</option>
		  </select>
		</div>

      <div>
        <button id="low-high">Price - low to high</button>
        <button id="high-low">Price - high to low</button>
      </div>
    </div>

    <!-- Items Grid -->
    <div class="items-container">
      <c:forEach var="item" items="${items}">
        <div class="item-card">
          <a href="/items/${item.itemId}" class="link-card">
            <img
              src="${
                    empty item.itemUrlList 
                      ? '/images/Thar.png' 
                      : item.itemUrlList[0].url
                  }"
              alt="${item.itemName} image"/>
          </a>
          <p>${item.itemName}</p>
          <p>Price: &#8377;${item.price}</p>
          <button class="cart-buttons" onclick="addToCart(${item.itemId})">
            Add to Cart
          </button>
        </div>
      </c:forEach>
    </div>
  </c:if>

  <script>
    window.itemsDataJson = ${jsonItems};
  </script>
  <script src="/js/sort.js"></script>
</body>
</html>
