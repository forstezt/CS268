<?php error_reporting(E_ALL); ?>
<html>
<head>
	<title>Catalog</title>
    <script type="text/javascript" src="JS/validate.js"></script>
    <link href="CSS/menu.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="maindiv">
<?php include("INCLUDE/menu.html"); 
@mysql_connect("dario.cs.uwec.edu","STUDENT","$5333") or die( "Unable to connect to MySQL" );
@mysql_select_db("STUDENT") or die( "Unable to select database");

$query = "SELECT part_number, part_description, price
          FROM part
		  ORDER BY part_description";

$result=mysql_query($query) or die("Query failed");
$numrows=mysql_numrows($result);
mysql_close();
if($numrows > 0) {
?>
	<form name="items" action="SessionAddToCart.php" onSubmit="return validate();">
        <table id="dataTable">
             <tr><th>Item</th><th>Price</th><th>&nbsp;</th></tr>
        <?php while ($row = mysql_fetch_array($result)) { ?>
             <tr><td><?php echo $row["part_description"] ?></td>
                 <td align="right">$<?php echo $row["price"] ?></td>
                 <td><input type="radio" name="item" 
                            value="<?php echo $row["part_number"] . ":" . 
					                          $row["part_description"] . ":" . 
									          $row["price"]; ?>" 
                            onclick="document.items.quantity.select();"/></td>
             </tr>
        <?php } ?>
        <tr><td align="right">Quantity: </td>
            <td><input type="text" name="quantity" maxlength="2" size="5" /></td>
            <td><input type="submit" value="Add to Cart" /></td></tr>
        </table>
    </form>
<?php
} else { ?>
	<center><h2>No items were found</h2></center><br />
<?php 
} ?>
</div>
</body>
</html>