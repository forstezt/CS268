function deleteCustomer(cust_id, cust_name) {
	if(confirm("Delete selected customer?")) {
		window.location.href="deleteCustomer?cust_id=" + cust_id + "&cust_name=" + cust_name;
	}
}