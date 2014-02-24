function deleteCustomer(cust_id, cust_name) {
	if(confirm("Update customer information?")) {
		window.location.href="deleteCustomer?cust_id=" + cust_id + "&cust_name=" + cust_name;
	}
}