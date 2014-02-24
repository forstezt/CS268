<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <script src="JSLibrary/inputCustomer.js"></script>
  <link href="CSS/styles.css" rel="stylesheet" type="text/css" />
  <title>Edit Customer</title>
</head>
<body onload=formLoad(document.frmCustomer.cust_name);>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
      	<s:form key="frmCustomer" cssClass="dataTable" action="updateCustomer" onsubmit="return validate(document.frmCustomer);">
   		<input type="hidden" name="c.cust_id" value="${c.cust_id}" />
       	<table>
  			<tr>
				<td colspan=2 align="center" bgcolor="#808080"><font size=6 color="#FFFFFF"><b>Edit Customer</b></font></td>	
 			</tr>
			<s:textfield key="c.cust_name" label="Name" />
			<s:select key="c.cust_type" label="Type" 
			          headerKey="0" headerValue="Select Customer Type"
			          list="custtypes" listKey="cust_type" listValue="cust_type_desc" />
			<s:textfield key="c.cust_addr" label="Address" />
			<s:textfield key="c.cust_zip" label="Zip Code" />
			<s:textfield key="c.cust_phone" label="Phone" />
			<tr><td>&nbsp;</td><td><input type="submit" value="Submit Changes" /></td></tr>		
		</table>
		</s:form>    
	</div>
</div>
</body>
</html>