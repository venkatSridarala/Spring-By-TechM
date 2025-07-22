/**
 * 
 */

const cancelItem = async (id) => {
	try {
		const response = await fetch("/orders/" + id, {
			method: "PUT"
		});
		
		if(!response.ok) throw new Error("Error");
		
		const result = await response.text();
		alert(result);
		location.reload();
	} catch (error) {
		alert(error.message || "An error occured");
	}
}