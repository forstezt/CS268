<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body class="dataTable">
   <table>
     	<tr><th>Product</th><th>Cost</th><th>Price</th></tr>
   	 	<c:forEach var="curLine" items="${data}"> 
      	<tr>
	   		<td align="left">${curLine.prod_desc}</td>
	   		<td align="right">${curLine.prod_cost}</td>
	   		<td align="right">${curLine.prod_price}</td>
	 	</tr>	  
    	</c:forEach>
     <tr>
       <td align="left">
	       <form action="input">
	         <input type="submit" value="Add product" />
	       </form>
       </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
      <tr><td align="center" colspan="3">${msg}</td></tr>
   </table>
</body>
</html>
 