


const editButton = document.getElementById("edit");

editButton.addEventListener("click", () => {
	document.querySelector('.profile-container').classList.add('editing');
	newAddressButton.style.display = "none";
	editButton.style.display = "none";
});

const newAddressButton = document.getElementById("new-address");
const dataAttributes = [
	{
		label: "Door No",
		type: "text",
		name: "doorno"
	}, {
		label: "Area",
		type: "text",
		name: "area"
	}, {
		label: "City",
		type: "text",
		name: "city"

	}, {
		label: "Pincode",
		type: "number",
		name: "pincode"
	}, {
		label: "State",
		type: "text",
		name: "state"
	},

];

const createNewBox = (addressSection) => {
	const divFieldGroup = document.createElement("div");
	const fieldDivs = [];
	for (let i = 0; i < 5; i++) {
		const field = document.createElement("div");
		const label = document.createElement("label");
		const input = document.createElement("input");

		label.innerText = dataAttributes[i].label;
		input.setAttribute("type", dataAttributes[i].type);
		input.setAttribute("name", dataAttributes[i].name);
		input.setAttribute("id","input-show");
		input.setAttribute("required", true);
		field.setAttribute("class", "field");

		field.append(label);
		field.append(input);
		fieldDivs.push(field);
	}
	const tempDiv = document.createElement("div");
	fieldDivs.forEach((field) => {
		tempDiv.append(field);
	});
	const buttonDiv = document.createElement("div");
	buttonDiv.setAttribute("class","field");
	const button = document.createElement("button");
	button.innerText = "submit";
	
	buttonDiv.append(button);
	
	tempDiv.append(buttonDiv);
	const form = document.createElement("form");
	form.setAttribute("method","post");
	form.setAttribute("action","/profile/address");
	
	form.append(tempDiv);
	divFieldGroup.append(form);
	divFieldGroup.classList.add("field-group");
	divFieldGroup.classList.add("address-field");
	addressSection.append(divFieldGroup);
}


//const submitButtonNewAddress = document.getElementById("new-address-submit");

newAddressButton.addEventListener("click", () => {

	const addressSection = document.getElementById("address-section");
	const addresses = addressSection.querySelectorAll(".field-group");
	//if (addresses.length == 2) {
		//newAddressButton.hidden = true;
	//}
	editButton.hidden = true;
	newAddressButton.hidden = true;
	//submitButtonNewAddress.hidden = false;
	createNewBox(addressSection);
});