<html>
<head>
	<title>JQuery</title>
    <script src="jquery.js"></script>
    <script src="myFunctions.js"></script>
</head>
<body>
<?php error_reporting(E_ALL); 
$query="SELECT cust_id, cust_name FROM candy_customer ORDER BY cust_name";

mysql_connect("dario.cs.uwec.edu","CS319STU","class") 
	or die("Could not connect to MySQL The reported error is:<br />" . mysql_error());
	
mysql_select_db("CS319STU")                           
	or die("Could not connect to database. The SQL error is:<br />" . mysql_error());
	
$result=mysql_query($query)                           
	or die("SQL Error: <b>" . mysql_error() . "</b><br />");
	
$numrows=mysql_numrows($result);
mysql_close();
?>
Display Orders For: <select id="customerid">
    <option value="-1">Select a Customer</option>
<?php while ($row = mysql_fetch_array($result)) { ?>
    <option value="<?php echo $row["cust_id"] ?>"><?php echo $row["cust_name"] ?></option>
<?php } ?>
</select>
<div id="orderInfo"></div>       
</body>
</html> 