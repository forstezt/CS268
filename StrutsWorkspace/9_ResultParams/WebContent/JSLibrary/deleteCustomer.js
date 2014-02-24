function deleteCustomer(cust_id, cust_name) {
	if(confirm("Delete selected customer?")) {
		window.location.href="deleteCustomer?c.cust_id=" + cust_id + "&c.cust_name=" + cust_name;
	}
}