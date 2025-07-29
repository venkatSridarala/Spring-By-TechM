/**
 * 
 */

 const editButton = document.getElementById("edit");

editButton.addEventListener("click", () => {
	document.querySelector('.profile-container').classList.add('editing');
	editButton.style.display = "none";
});