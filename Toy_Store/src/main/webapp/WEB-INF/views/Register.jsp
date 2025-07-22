<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>For More On Toy's Store - Sign Up</title>
<link rel="stylesheet" href="/css/register-style.css">
</head>
<body>
    <div class="signup-container">
        <h1>Create Account</h1>
        <form method="post" action="register">
            <!-- Personal Info -->
            <label for="firstname">Firstname:</label>
            <input type="text" id="firstname" name="firstname" required/>

            <label for="lastname">Lastname:</label>
            <input type="text" id="lastname" name="lastname" required/>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required/>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required/>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required/>

            <label for="doorNo">Door Number:</label>
            <input type="text" id="doorNo" name="doorNo" required/>

            <label for="area">Area:</label>
            <input type="text" id="area" name="area" required/>

            <label for="city">City:</label>
            <input type="text" id="city" name="city" required/>

            <label for="state">State:</label>
            <input type="text" id="state" name="state" required/>

            <label for="pinCode">Pincode:</label>
            <input type="number" id="pinCode" name="pinCode" required/>

            <input type="submit" value="Sign Up"/>
        </form>
    </div>
</body>
</html>
