<?php error_reporting(E_ALL); 
$query="SELECT purch_date, prod_desc, prod_cost, pounds 
        FROM candy_purchase p JOIN candy_product pr ON pr.prod_id = p.prod_id
        WHERE p.cust_id = " . $_REQUEST["cust_id"];

mysql_connect("dario.cs.uwec.edu","CS319STU","class") 
	or die("Could not connect to MySQL The reported error is:<br />" . mysql_error());
	
mysql_select_db("CS319STU")                           
	or die("Could not connect to database. The SQL error is:<br />" . mysql_error());
	
$result=mysql_query($query)                           
	or die("SQL Error: <b>" . mysql_error() . "</b><br />");
	
$numrows=mysql_numrows($result);
mysql_close();
?>
<table border="4" cellpadding="3" cellspacing="1">
<?php
if($numrows == 0 && $_REQUEST["cust_id"] == -1) {
       // show nothing - user selected the 'Select a Customer' entry in the list
      } else if($numrows == 0) { ?>
<tr><td colspan="6" align="center">No orders were found for the selected customer</td></tr>
<?php } else { 
          $totalCost = 0;
          $itemCost = 0; ?>
<table border="4" cellpadding="3" cellspacing="1">
    <tr><th>Order Date</th><th>Product</th><th>CostPerLb</th><th>Pounds</th><th>Item Cost</th></tr>               
<?php     while ($row = mysql_fetch_array($result)) { 
            $itemCost = $row["prod_cost"] * $row["pounds"]; 
            $totalCost = $totalCost + $itemCost; ?>
            <tr><td><?php echo $row["purch_date"] ?></td>
                <td><?php echo $row["prod_desc"] ?></td>
                <td align="right">$<?php echo number_format($row["prod_cost"], 2) ?></td>
                <td align="right"><?php echo number_format($row["pounds"], 2) ?></td>
                <td align="right">$<?php echo number_format($itemCost, 2) ?></td>
            </tr>
<?php     } ?>
          <tr><td colspan="6" align="right"><b>Total Order Costs: $<?php echo number_format($totalCost, 2) ?></b></td></tr>
<?php } ?>
</table>





