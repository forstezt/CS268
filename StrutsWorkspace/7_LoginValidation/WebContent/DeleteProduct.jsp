<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
	<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
   	<title>Delete Product</title>
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
	<div id="maincontent">
		<div class="dataTable">
			<s:form name="frmDelete" action="delete">
				<s:select key="prod_id" label="Products" 
				          headerKey="0" headerValue="Select Product"
				          list="data" 
				          listKey="prod_id" 
				          listValue="prod_desc"/>
				<s:submit align="left" value="Delete" />
			</s:form>
   			<center>${error_msg}</center>
		</div>
	</div>	
</div>
</body>
</html>