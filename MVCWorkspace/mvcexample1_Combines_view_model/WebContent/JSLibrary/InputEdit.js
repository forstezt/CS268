	function validate() {
		if(document.frmEdit.prod_desc.value == "") {
			alert("Please enter the product description");
			document.frmEdit.prod_desc.focus();
			return false;
		}	
		if(document.frmEdit.prod_cost.value == "") {
			alert("Please enter the cost");
			document.frmEdit.prod_cost.focus();
			return false;
		}
		if(isNaN(document.frmEdit.prod_cost.value) == true) {
			alert("Please enter number for the cost");
			document.frmEdit.prod_cost.select();
			return false;
		}
		if(document.frmEdit.prod_price.value == "") {
			alert("Please enter the price");
			document.frmEdit.prod_price.focus();
			return false;
		}
		if(isNaN(document.frmEdit.prod_price.value) == true) {
			alert("Please enter number for the price");
			document.frmEdit.prod_price.select();
			return false;
		}		
		return true;
	}