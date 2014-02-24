	function updateProduct(prod_id, prod_desc) {
		window.location.href="EditSelectedProduct?prod_id=" + prod_id;
	}
	function deleteProduct(prod_id, prod_desc) {
		if(confirm("OK to confirm deleting " + prod_desc)) {
			window.location.href="Delete?prod_id=" + prod_id + "&prod_desc=" + prod_desc;
		}
	}	