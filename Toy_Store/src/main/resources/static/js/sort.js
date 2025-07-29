/**
 * 
 */


const cartData = JSON.parse(localStorage.getItem("cartData")) || [];
const cartAnchorElement = document.getElementById("cart");
if(cartData.length > 0) cartAnchorElement.innerText = `Cart ${cartData.length}`;

const totalData = window.itemsDataJson;
let manuplateData = [...totalData];
const ascendingOrder = (a, b) => a.price - b.price;

const descendingOrder = (a, b) => b.price - a.price;

const lowToHighButton = document.getElementById("low-high");

lowToHighButton.addEventListener("click", () => {
	sortByPrice(ascendingOrder);
})

const highToLow = document.getElementById("high-low");

highToLow.addEventListener("click", () => {
	sortByPrice(descendingOrder);
});

const formatPrice = (p) => {
	let price = `${p}`
	if (!price.includes('.')) price = price + ".0";
	return price;
}

const sortByPrice = (orderFn) => {
	const data = manuplateData;
	const cardContainer = document.querySelector(".items-container");
	//const data = JSON.parse(cardContainer.dataset.category);
	const childrens = Array.from(cardContainer.children);
	data.sort(orderFn);
	childrens.map((card, index) => {
		const srcUrl = data[index].itemUrlList.length == 0 ? "/images/apple.jpg": data[index].itemUrlList[0].url;
		//card.children[0].src = srcUrl;
		//card.children[0].alt = `${data[index].itemName} image`;
		card.children[0].href = `/items/${data[index].itemId}`;
		card.children[0].children[0].src = srcUrl;
		card.children[0].children[0].alt = `${data[index].itemName} image`;
		card.children[1].innerHTML = data[index].itemName;
		const price = formatPrice(data[index].price);
		card.children[2].innerHTML = `Price: ${price}`;
		card.children[3].setAttribute("onclick",`addToCart(${data[index].itemId})`);
	})
}

const constructCard = (itemObj, cardContainer) => {
	const div = document.createElement("div");
	const img = document.createElement("img");
	const p1 = document.createElement("p");
	const p2 = document.createElement("p");
	const button = document.createElement("button");
	const anchor = document.createElement("a");
	
	const imageUrl = itemObj.itemUrlList.length == 0 ? "/images/apple.jpg": itemObj.itemUrlList[0].url;
	img.setAttribute("src", imageUrl);
	img.setAttribute("alt", "apple image");
	div.setAttribute("class", "item-card");
	anchor.setAttribute("href",`/items/${itemObj.itemId}`);
	anchor.setAttribute("class","link-card");
	button.setAttribute("onclick",`addToCart(${itemObj.itemId})`);
	button.setAttribute("class","cart-buttons");
		
	p1.innerText = itemObj.itemName;
	const price = formatPrice(itemObj.price);
	p2.innerText = `Price: ${price}`;
	button.innerText = "Add to Cart";
	anchor.append(img);
	div.append(anchor);
	div.append(p1);
	div.append(p2);
	div.append(button);
	cardContainer.append(div);

}

const selectElement = document.getElementById("category");
selectElement.addEventListener("change", (event) => {
	document.getElementById("search").value = "";
	const selectedCategory = event.target.value;
	const data = [...totalData];
	manuplateData = [];
	const cardContainer = document.querySelector(".items-container");
	cardContainer.innerHTML = "";
	data.forEach((itemObj) => {
		if (selectedCategory !== "all") {
			if (itemObj.category === selectedCategory) {
				constructCard(itemObj, cardContainer);
				manuplateData.push(itemObj);
			}
		} else {
			constructCard(itemObj, cardContainer);
			manuplateData.push(itemObj);
		}
	})
})

const categoryFilter = (data, manuplateData, selectedCategory) => {
	data.forEach((itemObj) => {
		if (selectedCategory !== "all") {
			if (itemObj.category === selectedCategory) {
				manuplateData.push(itemObj);
			}
		} else {
			manuplateData.push(itemObj);
		}
	})
}

const search = document.getElementById("search");
search.addEventListener("input", (event) => {
	const searchValue = event.target.value.trim().toLowerCase();
	const selectedCategory = document.querySelector("select").value;
	manuplateData = [];
	const data = [...totalData];
	categoryFilter(data, manuplateData, selectedCategory);
	const searchData = manuplateData.filter((item) => item.itemName.toLowerCase().includes(searchValue));
	manuplateData = [...searchData];
	const cardContainer = document.querySelector(".items-container");
	cardContainer.innerHTML = "";
	searchData.forEach((itemObj) => constructCard(itemObj, cardContainer));
})


const addToCart = (id) => {
	const cartData = JSON.parse(localStorage.getItem("cartData")) || [];
	const cartAnchorElement = document.getElementById("cart");
	const [item] = totalData.filter(item => item.itemId === id);
	if(item.availableQuantity < 1){
		alert("Less in quantity");
		return;
	}
	const cartItem = cartData.filter(item => item.itemId === id);
	if(cartItem.length > 0){
		//console.log("avail: "  + item.availableQuantity + " Cart Item: " +cartItem.quantity);
		if(item.availableQuantity > cartItem[0].quantity){
			cartItem[0].quantity = cartItem[0].quantity + 1;
			localStorage.setItem("cartData",JSON.stringify(cartData));
		}else{
			//console.log("CartItem: " + cartItem[0].quantity + "Item: " + item.availableQuantity);
			alert("Unable to increase quantity");
			return;
		}
	}else{
		const updatedItem = {...item,quantity:1};
		cartData.push(updatedItem);
		cartAnchorElement.innerText = `Cart ${cartData.length}`
		localStorage.setItem("cartData",JSON.stringify(cartData));
	}
}