<?php 
	session_start();
	error_reporting(E_ALL);
	$quantity = $_REQUEST["quantity"];
	$item = $_REQUEST["item"];
	$tempArray = explode(":", $item); // part_number, part_description, price in item
	$_SESSION["_" . $tempArray[0]] = $tempArray[1] . ":" . $tempArray[2] . ":" . $quantity;
?>
<html>
<head>
	<title>Catalog</title>
    <link href="CSS/menu.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="maindiv">
		<?php include("INCLUDE/menu.html");  ?>
        Added <?php echo $quantity . " " . $tempArray[1] . "(s) costing $" . $tempArray[2]; ?> each to the shopping cart
    </div>
</body>
</html>