
function removeFromCart(productId) {
    fetch(`/cart/remove?productId=${productId}`)
        .then(() => location.reload())
        .catch(err => alert("Failed to remove item"));
}
