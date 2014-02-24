<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Edit Product</title>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="JSLibrary/functions.js"></script>
</head>
<body>
<div id="bodyCenter">
	<div id="navigation">
		<%@ include file="Includes/NavBar.htm" %>
	</div>
	<div id="maincontent">
		<table class="dataTable">
		<tr><th align="left">&nbsp;Description&nbsp;</th>
		    <th align="left">&nbsp;Our Cost&nbsp;</th>
		    <th align="left">&nbsp;Retail Price&nbsp;</th>
		    <th>&nbsp;</th>
		</tr>
		
	    <c:forEach var="curLine" items="${product.data}">   
		    <tr><td align="left">${curLine.prod_desc}</td>  
		        <td align="right">${curLine.formattedCost}</td>  
		        <td align="right">${curLine.formattedPrice}</td> 
		        <td align="right"><input type="button" 
		                                 value="Edit" 
		                                 onclick="updateProduct('${curLine.prod_id}')" /></td>        
		    </tr>  
	    </c:forEach>	    
	    </table>
	    <br />
		<table class="msgTable"><tr><td align="left">${product.msg}&nbsp;</td></tr></table>
	</div>
</div>
</body>
</html>