<!DOCTYPE html>
<html>
<head>
	<title>Add Product</title>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="JSLibrary/validateInputs.js"></script>
</head>
<body onload="formLoad(document.frmInsert.prod_desc);">
<div id="bodyCenter">
	<div id="navigation">
		<%@ include file="Includes/NavBar.htm" %>
	</div>
	<div id="maincontent">
		<form name="frmInsert" action="Insert" onsubmit="return validate(this);">
			<table style="width:400px" class="dataTable">
				<tr><th colspan="2" align="left">&nbsp;Add a New Product</th></tr>
				<tr><td align="left">&nbsp;Description&nbsp;</td><td align="left"><input type="text" name="prod_desc" size="40" /></td></tr>
				<tr><td align="left">&nbsp;Our Cost&nbsp;</td><td align="left"><input type="text" name="prod_cost" /></td></tr>
				<tr><td align="left">&nbsp;Retail Price&nbsp;</td><td align="left"><input type="text" name="prod_price" /></td></tr>
				<tr><td>&nbsp;</td><td align="left"><input type="submit" value="Submit" /></td></tr>
			</table>
			<br />
		</form>
		<table class="msgTable"><tr><td align="left">${msg}&nbsp;</td></tr></table>
	</div>
</div>
</body>
</html>