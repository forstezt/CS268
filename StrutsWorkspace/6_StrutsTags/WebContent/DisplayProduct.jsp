<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body class="dataTable">
   <table>
     <tr><th>Product</th><th>Cost</th><th>Price</th></tr>
   <s:iterator value="data">
      <tr>
	   <td align="left">${prod_desc}</td>
	   <td align="right"><s:property value="prod_cost" /></td>
	   <td align="right"><s:property value="prod_price" /></td>
	 </tr>	  
   </s:iterator>
     <tr>
       <td align="left">
	       <s:form action="input">
	         <s:submit value="Add product" />      
	       </s:form>
       </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
   </table>
<center>${error_msg}</center>
</body>
</html>
 