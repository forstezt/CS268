<?php 
	session_start(); 
	error_reporting(E_ALL);
	// create some session variables
	$_SESSION["name"] = "Smith";
	$_SESSION["birthdate"] = "12/4/1991";
	$_SESSION["address"] = "1234 Cherry Lane";
	$_SESSION["city"] = "Eau Claire";
	$_SESSION["state"] = "WI";
	$_SESSION["zip"] = "54702";
?>
<!DOCTYPE html>
<html><head><title>Retrieve All Session Variables</title></head>
<style>
	table { border-color:#039;border-style:inset;border-width:5px;border-collapse:collapse; }
	td, th { border:inset 1px;text-align:left; }
</style>
<body>
<table>
	<tr><th>Associative Key</th><th>Value</th></tr>
<?php 
	foreach($_SESSION as $key => $value) { ?>
		<tr><td><?php echo $key; ?></td>
		    <td><?php echo $value; ?></td>
		</tr>
<?php 
    } ?>
</table>
</body></html>