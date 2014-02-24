function validate() {
	var checked = false;
	for(var i=0; i<document.items.item.length; i++) {
		if(document.items.item[i].checked) {
			checked = true;
			break;
		}
	}
	if(checked == false) {
		alert("Please select an item before adding to the shopping cart");
		document.items.item[0].focus();	
		return false;
	}	
	var qtyTest = /^\d{1,2}$/;
	if(!qtyTest.test(document.items.quantity.value)) {
		alert("Please enter the quantity to purchase before adding to the shopping cart");
		document.items.quantity.select();	
		return false;
	}
}

