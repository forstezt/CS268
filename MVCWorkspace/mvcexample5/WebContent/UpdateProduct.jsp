<!DOCTYPE html>
<html>
<head>
<title>Update Product</title>
<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JSLibrary/validateInputs.js"></script>
</head>
<body onload="formLoad(document.frmUpdate.prod_desc);">
<div id="bodyCenter">
	<div id="navigation">
		<%@ include file="Includes/NavBar.htm" %>
	</div>
	<div id="maincontent">
		<form name="frmUpdate" action="update" onsubmit="return validate(this);">
			<input type="hidden" name="prod_id" value="${product.prod_id}"} />
			<table class="dataTable">
				<tr><th colspan="2" align="left">&nbsp;Update Product</th></tr>
				<tr><td align="left">&nbsp;Description&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_desc" value="${product.prod_desc}" /></td></tr>
				<tr><td align="left">&nbsp;Our Cost&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_cost" value="${product.prod_cost}" /></td></tr>
				<tr><td align="left">&nbsp;Retail Price&nbsp;</td><td><input class="userInputWidth" type="text" name="prod_price" value="${product.prod_price}" /></td></tr>
				<tr><td>&nbsp;</td><td><input class="userInputWidth" type="submit" value="Submit" /></td></tr>
			</table>
			<br />
		</form>
		<table class="msgTable"><tr><td>${product.msg}&nbsp;</td></tr></table>
	</div>
</div>
</body>
</html>