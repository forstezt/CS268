<!DOCTYPE html>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Insert Product</title>
  <script src="Includes/InputInsert.js"></script>
</head>

<body>
<form name="frmInsert" action="insert.action">
	<table style="width:300px" class="dataTable">
	  <tr><th colspan="2" align="center">Add a New Product</th></tr>
	  <tr><td>Description </td>
	      <td><input type="text" name="prod_desc"></td></tr>
	  <tr><td>Our Cost </td>
	      <td><input type="text" name="prod_cost"></td></tr>
	  <tr><td>Retail Price </td>
	      <td><input type="text" name="prod_price"></td></tr>
	  <tr><td>&nbsp;</td>
	      <td><input type="submit" value="Submit" /></td></tr>
	</table>
</form>
</body>
</html>
