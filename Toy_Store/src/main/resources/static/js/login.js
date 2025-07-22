/**
 * 
 */

const form = document.getElementById("form");
const adminButton = document.getElementById("admin");
const userButton = document.getElementById("user");

 const adminLogin = () => {
	 form.setAttribute("action","/login/auth/admin");
	 adminButton.hidden = true;
	 userButton.hidden = false;
 }
 
 const userLogin = () => {
	 form.setAttribute("action","/login/auth");
	 userButton.hidden = true;
	 adminButton.hidden = false;
 }