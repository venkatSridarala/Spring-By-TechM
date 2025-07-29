/**
 * 
 */

const totalData = window.itemsDataJsonCart;
let cartData = JSON.parse(localStorage.getItem("cartData")) || [];

const totalPriceH4 = document.getElementById("total-amount");
let price = 0;

const cartItemsContainer = document.querySelector(".item-cart-container");

const create = (ele) => {
	return document.createElement(ele);
}

const formatPrice = (p) => {
	let price = `${p}`
	if (!price.includes('.')) price = price + ".0";
	return price;
}


const constructCartItem = (item) => {
	const divItemCart = create("div");
	const divItemDetails = create("div");
	const divItemInfo = create("div");
	const divItemActions = create("div");
	const image = create("img");
	const strongName = create("strong");
	const strongPrice = create("strong");
	const pName = create("p");
	const pPrice = create("p");
	const buttonMinus = create("button");
	const buttonPlus = create("button");
	const spanCount = create("span");

	divItemCart.setAttribute("class", "item-cart");
	divItemDetails.setAttribute("class", "item-details");
	divItemInfo.setAttribute("class", "item-info");
	divItemActions.setAttribute("class", "item-actions");
	
	const imageUrl = item.itemUrlList.length == 0 ? "/images/milk.jpeg": item.itemUrlList[0].url;
	
	image.setAttribute("src", imageUrl);
	image.setAttribute("alt", `${item.itemName} image`);
	buttonMinus.setAttribute("class", "decrement");
	buttonMinus.setAttribute("onclick", `minus(${item.itemId})`);
	buttonPlus.setAttribute("onclick", `plus(${item.itemId})`);
	buttonPlus.setAttribute("class", "increment");

	strongName.innerText = "Name:";
	strongPrice.innerText = "Price:";
	buttonMinus.innerText = "-";
	buttonPlus.innerText = "+";
	spanCount.innerText = item.quantity;

	pName.append(strongName);
	pName.append(item.itemName);
	pPrice.append(strongPrice);
	pPrice.append(formatPrice(item.price));
	divItemInfo.append(pName);
	divItemInfo.append(pPrice);
	divItemDetails.append(image);
	divItemDetails.append(divItemInfo);
	divItemCart.append(divItemDetails);
	divItemActions.append(buttonMinus);
	divItemActions.append(spanCount);
	divItemActions.append(buttonPlus);
	divItemCart.append(divItemActions);
	cartItemsContainer.append(divItemCart);

	price += (item.price * item.quantity);
}

const checkOutDiv = document.getElementById("checkout");
const decideCheckOutButton = () => {
	if (cartData.length > 0) {
		const allChildrens = Array.from(checkOutDiv.children);
		if (allChildrens.length === 1) return;
		const checkOutButton = document.createElement("a");
		checkOutButton.setAttribute("href", "/orders/address");
		//checkOutButton.setAttribute("id","checkout-button");
		checkOutButton.innerText = "Checkout";
		checkOutDiv.append(checkOutButton);
	} else {
		checkOutDiv.innerHTML = "";
	}
}
decideCheckOutButton();

const constructAllItems = () => {
	cartData.forEach(item => {
		constructCartItem(item);
	});

	totalPriceH4.innerText = `Total Amount: ${formatPrice(price)}`;
	price = 0;
}

constructAllItems();

const clearAndConstructAllItems = () => {
	cartItemsContainer.innerHTML = "";
	constructAllItems();
}

const minus = (id) => {
	const decrementItemIndex = cartData.findIndex(item => item.itemId === id);
	//decrementItem.quantity = decrementItem.quantity - 1;
	if (decrementItemIndex < 0) return;

	if (cartData[decrementItemIndex].quantity === 1) {
		cartData.splice(decrementItemIndex, 1);
	} else {
		cartData[decrementItemIndex].quantity = cartData[decrementItemIndex].quantity - 1;
	}
	clearAndConstructAllItems();
	decideCheckOutButton();
	localStorage.setItem("cartData", JSON.stringify(cartData));
}

const plus = (id) => {
	const incrementItemIndex = cartData.findIndex(item => item.itemId === id);
	const totalDataItemIndex = totalData.findIndex(item => item.itemId === id);

	if (incrementItemIndex < 0 || totalDataItemIndex < 0) return;

	if (cartData[incrementItemIndex].quantity === totalData[totalDataItemIndex].availableQuantity) {
		alert("Insufficient Quantity");
		return;
	} else {
		cartData[incrementItemIndex].quantity = cartData[incrementItemIndex].quantity + 1;
	}
	clearAndConstructAllItems();
	localStorage.setItem("cartData", JSON.stringify(cartData));
}










