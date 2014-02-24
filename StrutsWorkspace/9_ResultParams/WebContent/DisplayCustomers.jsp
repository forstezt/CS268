<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <title>Delete Customers</title>
  <link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
    	<div class="dataTable">
 			<table>
				<tr><th>&nbsp;Name&nbsp;</th>
				    <th>&nbsp;Type&nbsp;</th>
				    <th>&nbsp;Address&nbsp;</th>
				    <th>&nbsp;Zip&nbsp;</th>
				    <th>&nbsp;Phone&nbsp;</th>
			    </tr>
			<s:iterator value="customers">
				<tr>
					<td>&nbsp;&nbsp;${cust_name}&nbsp;</td>	 
					<td>&nbsp;&nbsp;${cust_type_desc}&nbsp;</td>
					<td>&nbsp;&nbsp;${cust_addr}&nbsp;</td>
					<td>&nbsp;&nbsp;${cust_zip}&nbsp;</td>
					<td>&nbsp;&nbsp;${cust_phone}&nbsp;</td>    				
				</tr>				
			</s:iterator>				 				
			</table>
		</div>
    </div>
</div>
</body>
</html>
