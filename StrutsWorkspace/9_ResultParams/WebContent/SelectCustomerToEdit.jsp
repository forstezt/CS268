<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <title>Edit Customers</title>
  <link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
    	<div class="dataTable">
			<table>
			<tr><td colspan="6" style="text-align:center;" ><span class="msg"><s:property value="msg" /></span></td></tr>  				
					<tr><th>&nbsp;Name&nbsp;</th>
				    <th>&nbsp;Type&nbsp;</th>
				    <th>&nbsp;Address&nbsp;</th>
				    <th>&nbsp;Zip&nbsp;</th>
				    <th>&nbsp;Phone&nbsp;</th>
				    <th>&nbsp;</th>
			    </tr>
			<s:iterator value="customers">
				<tr>
					<td>&nbsp;&nbsp;<s:property value="cust_name" />&nbsp;</td>	 
					<td>&nbsp;&nbsp;<s:property value="cust_type_desc" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_addr" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_zip" />&nbsp;</td>
					<td>&nbsp;&nbsp;<s:property value="cust_phone" />&nbsp;</td>  
			        <td align="right">
			        <input type="button" value="Edit" 
			               onclick="window.location.href='inputcustomeredit?c.cust_id=${cust_id}'" />
			        </td>   	
			    </tr>				
			</s:iterator>
			</table>			    
		</div>
    </div>
</div>
</body>
</html>
