<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link rel="stylesheet" href="/css/add-item-style.css" >
</head>
<body>
	<jsp:include page="AdminHeader.jsp" />
    <form method="post" action="/items/admin/add">
    	<h2 style="text-align: center;">Add new item</h2>
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required/>

        <label for="price">Price:</label>
        <input type="number" name="price" id="price" required/>

        <label for="weight">Weight:</label>
        <input type="text" name="weight" id="weight" required/>

        <label for="category">Category:</label>
        <select name="category" id="category" required>
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

        <label for="description">Description:</label>
        <textarea name="description" id="description" rows="3" cols="30" required></textarea>

        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" id="quantity" required/>

        <label>Image URLs:</label>
        <div id="image-urls">
            <input type="text" name="imageUrls" required/>
        </div>

        <div style="display: flex; gap: 10px;">
            <button type="button" id="add">Add</button>
            <button type="button" id="remove" hidden="true">Remove</button>
        </div>

        <input type="submit" value="Submit" />
    </form>

    <script type="text/javascript" src="/js/addItem.js"></script>
</body>
</html>
