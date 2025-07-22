/**
 * 
 */
let cartData = JSON.parse(localStorage.getItem("cartData")) || [];

 const selectAddress = async () => {
	 const selected = document.querySelector('input[name="addressId"]:checked');
	 const button = document.getElementById("buy");
	 
	if(cartData.length === 0){
		window.location.href = "http://localhost:8080/items";
		return;
	}
	try {
		button.innerText = "Ordering...";
		button.setAttribute("disabled","true");
		const updatedCartData = cartData.map((item) => {
			return {
				itemId:item.itemId,
				quantity:item.quantity
			}
		});
		
		const cartAddress = {
			items:updatedCartData,
			addressId:selected.value
		};
		
		const response = await fetch("/orders", {
			method: "POST",
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(cartAddress)
		});

		if(!response.ok){
			throw new Error("an error");
		}
		
		const result = await response.text();
		if(result === "Order received successfully"){
			cartData = [];
			//button.remove();
			window.location.replace("http://localhost:8080/orders");
			localStorage.setItem("cartData", JSON.stringify(cartData));
		}
	} catch (error) {
		button.innerText = "Buy";
		button.removeAttribute("disabled");
		alert("An error occured");
		console.log(error);
	}	 
 }