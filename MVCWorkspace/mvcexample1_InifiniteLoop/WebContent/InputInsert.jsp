<!DOCTYPE html>
<html>
<head>
	<title>Insert Product</title>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="Includes/InputInsert.js"></script>
<body onload="formLoad();">
<div id="bodyCenter">
	<div id="navigation">
		<%@ include file="Includes/NavBar.htm" %>
	</div>
	<div id="maincontent">
		<form name="frmInsert" action="insert.action">
			<table width="300px" class="dataTable">
				<tr><th colspan="2" align="left">&nbsp;Add a New Product</th></tr>
				<tr><td align="left">&nbsp;Description&nbsp;</td><td><input type="text" name="prod_desc"></td></tr>
				<tr><td align="left">&nbsp;Our Cost&nbsp;</td><td><input type="text" name="prod_cost"></td></tr>
				<tr><td align="left">&nbsp;Retail Price&nbsp;</td><td><input type="text" name="prod_price"></td></tr>
				<tr><td>&nbsp;</td><td><input type="submit" value="Submit" /></td></tr>
			</table>
			<br /><br />
		</form>
		${product.error_msg}
	</div>
</div>
</body>
</html>