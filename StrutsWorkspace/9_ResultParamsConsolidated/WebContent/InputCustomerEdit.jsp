<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <script src="JSLibrary/inputCustomer.js"></script>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Edit Customer</title>
</head>
<body onload=formLoad(document.frmCustomer.cust_name);>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.txt" %>
    <div id="maincontent">
       	<s:form key="frmCustomer" cssClass="dataTable" action="updateCustomer" onsubmit="return validate(document.frmCustomer);">
      		<input type="hidden" name="cust_id" value="${cust_id}" />
 			<tr>
				<td colspan=2 align="center" bgcolor="#808080"><font size=6 color="#FFFFFF"><b>Edit Customer</b></font></td>	
 			</tr>
			<s:textfield key="cust_name" label="Name" />
			<s:select key="cust_type" label="Type" 
			          headerKey="0" headerValue="Select Customer Type"
			          list="tData" listKey="cust_type" listValue="cust_type_desc" />
			<s:textfield key="cust_addr" label="Address" />
			<s:textfield key="cust_zip" label="Zip Code" />
			<s:textfield key="cust_phone" label="Phone" />
			<tr><td>&nbsp;</td><td><input type="submit" value="Submit Changes" /></td></tr>		
			<tr><td colspan="2" style="text-align:center">
			<span class="msg"><s:property value="msg" /></span></td></tr>		
		</s:form>    
	</div>
</div>
</body>
</html>