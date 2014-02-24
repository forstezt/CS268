	function updateProduct(prod_id) {
		window.location.href="editproduct?prod_id=" + prod_id;
	}
	function deleteProduct(prod_id, prod_desc) {
		if(confirm("Delete " + prod_desc + "?")) {
			window.location.href="delete?prod_id=" + prod_id + "&prod_desc=" + prod_desc;
		}
	}	