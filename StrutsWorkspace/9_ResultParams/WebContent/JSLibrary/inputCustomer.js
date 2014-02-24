	function formLoad(obj) {
		obj.focus();
	}
	function validate(obj) {
		if(obj.cust_name.value == "") {
			alert("Please enter the customer's name");
			obj.cust_name.focus();
			return false;
		}
		if(obj.cust_type.value == 0) {
			alert("Please select the customer's type");
			obj.cust_type.focus();
			return false;
		}	
		if(obj.cust_addr.value == 0) {
			alert("Please enter the customer's address");
			obj.cust_addr.focus();
			return false;
		}	
		if(obj.cust_zip.value == 0) {
			alert("Please enter the customer's postal code");
			obj.cust_zip.focus();
			return false;
		}	
		if(obj.cust_phone.value == 0) {
			alert("Please enter the customer's phone number");
			obj.cust_phone.focus();
			return false;
		}		
		return confirm("Continue?");
	}