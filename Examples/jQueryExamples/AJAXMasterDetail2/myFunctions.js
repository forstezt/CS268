$(function() {
    $.ajaxSetup ({ cache: false });
    
    $("#customerid").change(function() {    
        var cust_id = $(this).attr("value");
        var loadUrl = "OrderDetails.php?cust_id=" + cust_id; 
        var ajax_load = "<img src='load.gif' alt='loading...' />";
        $("#orderInfo").html(ajax_load).load(loadUrl);      
    });
});
