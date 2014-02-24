<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Insert Product</title>
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
	<div id="maincontent">
		<div class="dataTable">
			<s:form key="frmInsert" action="insert">
			  <tr><th colspan="2" align="center">Add a New Product</th></tr>
			  <s:textfield key="prod_desc" label="Description" />
			  <s:textfield key="prod_cost" label="Our Cost" />
			  <s:textfield key="prod_price" label="Retail Price" />
			  <s:submit value="Submit"/>
			</s:form>
		</div>
   		<center>${error_msg}</center>
	</div>
</div>
</body>
</html>
