<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body>
   <table class="dataTable">
     <tr><th>Product</th><th>Cost</th><th>Price</th></tr>
<c:forEach var="row" items="${data}">
	 <tr>
	   <td align="left">${row.prod_desc}</td>
	   <td align="right">${row.prod_cost}</td>
	   <td align="right">${row.prod_price}</td>
	 </tr>
</c:forEach>
     <tr>
       <td align="left">
         <input type="button" value="Add product" onclick="location.href='input';" />
       </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
   </table>
   <br />
   ${msg}
</body>
</html>
 