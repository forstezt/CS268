	function updateProduct(prod_id, prod_desc) {
		window.location.href="EditProduct.action?prod_id=" + prod_id + "&prod_desc=" + prod_desc;
	}
	function deleteProduct(prod_id, prod_desc) {
		window.location.href="delete.action?prod_id=" + prod_id + "&prod_desc=" + prod_desc;
	}	