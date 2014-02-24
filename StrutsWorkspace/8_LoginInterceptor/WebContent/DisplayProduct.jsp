<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body>
<div id="bodyCenter">
   <%@ include file="IncludeLibrary/NavBar.htm" %>
   <div id="maincontent">
	   <div class="dataTable">
		   <table>
		     <tr><th>Product</th><th>Cost</th><th>Price</th></tr>
				<s:iterator value="data">
				  <tr>
					<td align="left"><s:property value="prod_desc" /></td>
					<td align="right"><s:property value="prod_cost" /></td>
					<td align="right"><s:property value="prod_price" /></td>
				  </tr>	  
				</s:iterator>
		   </table>
	   </div>
	   <center>${error_msg}</center>
   </div>
</div>
</body>
</html>
 