<?php 
	session_start();
	error_reporting(E_ALL);
?>
<html>
<head>
	<title>Catalog</title>
    <link href="CSS/menu.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="maindiv">
<?php include("INCLUDE/menu.html");  ?>
   <h2>Shopping Cart</h2>
    <table id="dataTable">
		<tr><th>Item#</th>
            <th>Description</th>
            <th style="text-align:right;">Price</th>
            <th style="text-align:right;">Qty</th>
            <th style="text-align:right;">Cost</th>
            <th>&nbsp;</th>
        </tr>
<?php
    $total = 0;
	foreach ($_SESSION as $part_number => $value){ 
	   if(substr($part_number, 0, 1) == "_") {
		   $tempArray = explode(":", $value); // part_description, price, quantity
		   $cost = $tempArray[1] * $tempArray[2];
		   $total += $cost;
		   
		   ?>
		   <tr><td><?php echo substr($part_number, 1); ?></td>
			   <td><?php echo $tempArray[0]; ?></td>
			   <td align="right"><?php echo number_format($tempArray[1], 2); ?></td>
               <td align="right"><?php echo $tempArray[2] ?></td>
			   <td align="right"><?php echo number_format($cost, 2); ?></td>
			   <td><input type="button" 
						  onclick="window.location.href='SessionRemoveFromCart.php?part_number=<?php echo $part_number; ?>'" 
						  value="Remove" /></td>
		   </tr>
<?php
	   }
	}
?>
	   <tr><td>&nbsp;</td>
       	   <td>&nbsp;</td>
           <td>&nbsp;</td>
           <td align="right"><strong>Total: </strong></td>
           <td align="right"><strong>$<?php echo number_format($total, 2); ?></strong></td>
           <td>&nbsp;</td>
       </tr>
	</table>
</div>
</body>
</html>