/**
 * 
 */

 const updateStatus = async (event,id) => {
	 /* console.log(id);
	    console.log(event); */
	    const status = event.target.value;
	 try{
		 const response = await fetch("/orders/admin",{
			 method:"PUT",
			 headers:{
				 "Content-Type":"application/x-www-form-urlencoded"
			 },
			 body: new URLSearchParams({
				 id,
				 status
			 })
		 });
		 
		 if(!response.ok) throw new Error("Error");
		 const result = await response.text();
		 location.reload();
	 }catch(error){
		 alert(error.message || "An error occured")
	 } 
 }