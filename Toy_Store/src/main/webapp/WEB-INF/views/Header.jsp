
<!-- Header with links -->
  <div class="header">
   <div class="logo">
   <button class="menu-toggle" onclick="toggleMenu()"> &#9776;</button>
    <h1><span class="icon"></span>Toy&#8217;s Store</h1>
  </div>
      <div>
      	  <a href="/profile">Profile</a>
          <a href="/items">Toy Items</a>
      </div>
      <div>
      	  <a href="/orders">My Orders</a>
          <a href="/cart" id="cart">Cart</a>
          <a href="/login/logout">Logout</a>
      </div>
  </div>
  <script>
  function toggleMenu() {
    document.querySelector('header nav').classList.toggle('open');
  }
</script>
