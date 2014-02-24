	function deleteProduct(prod_id) {
		if(confirm("Are you sure you want to delete this product?")) {
			window.location.href="delete?prod_id=" + prod_id;
		}
	}	
	function updateProduct(prod_id) {
		window.location.href="updateproduct?prod_id=" + prod_id;
	}		