$(function() {
    $.ajaxSetup ({ cache: false });   
    $(".customerid").click(function() {
        var cust_id = $(this).attr("value");
        loadOrderDetail(cust_id);
    });
});
function loadOrderDetail(cust_id) {
	if(cust_id == -1) {
		$("#orderInfo").load("pleaseSelect.htm");
	} else if(cust_id == 0) {
		$("#orderInfo").load("detail1.htm");  
	} else if(cust_id == 1) {
		$("#orderInfo").load("detail2.htm");  
	} else {
		$("#orderInfo").load("notfound.htm");  
	}
}

