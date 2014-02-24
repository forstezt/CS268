<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Insert Product</title>
</head>
<body>
<form name="frmInsert" action="insert">
<table width="300px" class="dataTable">
  <tr><th colspan="2" align="center">Add a New Product</th></tr>
  <tr><td align="left">Description </td><td><input type="text" name="p.prod_desc"></td></tr>
  <tr><td align="left">Our Cost </td><td><input type="text" name="p.prod_cost"></td></tr>
  <tr><td align="left">Retail Price </td><td><input type="text" name="p.prod_price"></td></tr>
  <tr><td>&nbsp;</td><td><input type="submit" value="Submit" /></td></tr>
</table>
</form>
<input type="button" value="Delete products" onclick="location.href='deleteinput';" />
<input type="button" value="Display products" onclick="location.href='display';" />
</body>
</html>
