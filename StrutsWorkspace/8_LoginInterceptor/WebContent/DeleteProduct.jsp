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
		   <table>
		     <tr><th>Product</th><th>Cost</th><th>Price</th><th>&nbsp;</th></tr>
				<s:iterator value="data">
				  <tr>
					<td align="left"><s:property value="prod_desc" /></td>
					<td align="right"><s:property value="prod_cost" /></td>
					<td align="right"><s:property value="prod_price" /></td>
				  	<td align="left"><input type="button" value="Delete" 
				  	                        onclick="location.href='delete?prod_id=${prod_id}&prod_desc=${prod_desc}'" /></td>
				  </tr>	 
				</s:iterator>
		   </table>		

   			<center>${delete_msg}</center>
		</div>
	</div>	
</div>
</body>
</html>