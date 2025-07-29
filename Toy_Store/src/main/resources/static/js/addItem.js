/**
 * 
 */

 const itemURLDiv = document.getElementById("image-urls");
 const addButton = document.getElementById("add");
 const removeButton = document.getElementById("remove");
 
addButton.addEventListener("click",() => {
	if(itemURLDiv.children.length == 2){
		addButton.hidden = true;
	}
	const input = document.createElement("input");
	input.setAttribute("required",true);
	input.setAttribute("type","text");
	input .setAttribute("name","imageUrls");
	itemURLDiv.append(input);
	if(itemURLDiv.children.length >= 2){
		removeButton.hidden = false;		
	}
});

removeButton.addEventListener("click",() => {
	if(itemURLDiv.children.length == 2){
		removeButton.hidden = true;
	}
	itemURLDiv.children[itemURLDiv.children.length - 1].remove();
	if(addButton.hidden) addButton.hidden = false;
})