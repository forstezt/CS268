	function formLoad(input) {
		input.focus();
	}
	function validate(frm) {
		if(frm.prod_desc.value == "") {
			alert("Please enter the product description");
			frm.prod_desc.focus();
			return false;
		}	
		if(frm.prod_cost.value == "") {
			alert("Please enter the cost");
			frm.prod_cost.focus();
			return false;
		}
		testNum = /^[0-9]*\.?[0-9]+$/; 
		if(testNum.test(frm.prod_cost.value) == false) {
			alert("Please enter a number for the cost");
			frm.prod_cost.select();
			return false;
		}
		if(frm.prod_price.value == "") {
			alert("Please enter the price");
			frm.prod_price.focus();
			return false;
		}
		if(testNum.test(frm.prod_price.value) == false) {
			alert("Please enter a number for the price");
			frm.prod_price.select();
			return false;
		}	
		return confirm("Are you sure you want to add this product?");
	}