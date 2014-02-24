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
		<form name="frmInsert" action="insert" onsubmit="return validate(this);">
			<table class="dataTable">
				<tr><th colspan="2" align="left">&nbsp;Add a New Product</th></tr>
				<tr><td align="left">&nbsp;Description&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_desc" /></td></tr>
				<tr><td align="left">&nbsp;Our Cost&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_cost" /></td></tr>
				<tr><td align="left">&nbsp;Retail Price&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_price" /></td></tr>
				<tr><td>&nbsp;</td><td><input class="userInputWidth" type="submit" value="Submit" /></td></tr>
			</table>
			<br />
		</form>
		<table class="msgTable"><tr><td>${product.msg}&nbsp;</td></tr></table>
	</div>
</div>
</body>
</html>