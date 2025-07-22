/**
 * 
 */

 const updateAvailable = async (id) => {
	 console.log(id);
	 try{
		 const response = await fetch("/items/" + id,{
			 method:"PUT"
		 });
		 
		 if(!response.ok){
			 throw new Error("error");
		 }
		 
		 const result = await response.text();
		 location.reload();
	 }catch(error){
		 alert(error.message || "an error occured");
	 }
 }