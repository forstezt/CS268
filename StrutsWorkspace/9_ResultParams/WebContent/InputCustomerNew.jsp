<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <script src="JSLibrary/inputCustomer.js"></script>
  <link href="CSS/styles.css" rel="stylesheet" type="text/css" />
  <title>Add Customer</title>
</head>
<body onload=formLoad(document.frmCustomer.cust_name);>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
       	<s:form key="frmCustomer" cssClass="dataTable" action="insertCustomer" onsubmit="return validate(document.frmCustomer);">
       	<table>
			<tr>
				<td colspan=2 align="center" bgcolor="#808080"><font size=6 color="#FFFFFF"><b>Add New Customer</b></font></td>	
			</tr>
			<s:textfield key="c.cust_name" label="Name" value=""/>
			<s:select key="c.cust_type" label="Type" 
			          headerKey="0" headerValue="Select Customer Type"
			          list="custtypes" listKey="cust_type" listValue="cust_type_desc"/>
			<s:textfield key="c.cust_addr" label="Address" value="" />
			<s:textfield key="c.cust_zip" label="Zip Code" value="" />
			<s:textfield key="c.cust_phone" label="Phone" value="" />
			<tr><td>&nbsp;</td><td><input type="submit" value="Submit Customer" /></td></tr>		
		</table>
		</s:form>
		${msg}
	</div>
</div>
</body>
</html>