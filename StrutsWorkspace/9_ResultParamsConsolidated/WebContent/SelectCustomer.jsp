<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <title>Delete Customers</title>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <script src="JSLibrary/deleteCustomer.js"></script>
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.txt" %>
    <div id="maincontent">
    	<div class="dataTable">
			<table>
				<tr><th>&nbsp;Name&nbsp;</th>
				    <th>&nbsp;Type&nbsp;</th>
				    <th>&nbsp;Address&nbsp;</th>
				    <th>&nbsp;Zip&nbsp;</th>
				    <th>&nbsp;Phone&nbsp;</th>
				    <th>&nbsp;</th>
				    <th>&nbsp;</th>
			    </tr>
			<s:iterator value="cData">
				<tr>
					<td>&nbsp;&nbsp;<s:property value="cust_name" />&nbsp;</td>	 
					<td>&nbsp;&nbsp;<s:property value="cust_type_desc" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_addr" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_zip" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_phone" />&nbsp;</td>    				
			        <td align="right">
			        <input type="button" value="Edit" 
			               onclick="window.location.href='inputcustomeredit?cust_id=<s:property value="cust_id" />'" />
			        </td>   
			        <td align="right">
			        <input type="button" value="Delete" 
			               onclick="deleteCustomer(<s:property value="cust_id" />, '<s:property value="cust_name" />')" />
			        </td> 				
			        </tr>				
			</s:iterator>
				<tr><td colspan="7" style="text-align:center;" ><span class="msg"><s:property value="msg" /></span></td></tr>  				
			</table>
		</div>
    </div>
</div>
</body>
</html>
