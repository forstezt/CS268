<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
	<title>Delete Product</title>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="JSLibrary/functions.js"></script>
<div id="bodyCenter">
	<div id="navigation">
		<%@ include file="Includes/NavBar.htm" %>
	</div>
	<div id="maincontent">
		<table class="dataTable">
		<tr><th align="left">&nbsp;Prod ID&nbsp;</th>
		    <th align="left">&nbsp;Description&nbsp;</th>
		    <th align="left">&nbsp;Our Cost&nbsp;</th>
		    <th align="left">&nbsp;Retail Price&nbsp;</th>
		    <th>&nbsp;</th>
		</tr>
		
	    <c:forEach var="curLine" items="${data}">   
		    <tr><td align="left">${curLine.prod_id}</td> 
		        <td align="left">${curLine.prod_desc}</td>  
		        <td align="right">${curLine.formattedCost}</td>  
		        <td align="right">${curLine.formattedPrice}</td> 
		        <td align="right"><input type="button" 
		                                 value="Delete" 
		                                 onclick="deleteProduct('${curLine.prod_id}', '${curLine.escProd_desc}')" /></td>        
		    </tr>  
	    </c:forEach>	    
	    </table>
	    <br />
		<table class="msgTable"><tr><td align="left">${msg}&nbsp;</td></tr></table>
	</div>
</div>
</body>
</html>