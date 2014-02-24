<jsp:useBean id="p" scope="application" class="edu.uwec.cs.morriscm.Product" />
<% p.retrieveProductInformation(); %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html>
<html>
<head>
	<title>Bean Example (Model 1)</title>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="bodyCenter">
	<div id="maincontent">
		<table class="dataTable">
		<tr><th align="left">&nbsp;Prod ID&nbsp;</th>
		    <th align="left">&nbsp;Description&nbsp;</th>
		    <th align="left">&nbsp;Our Cost&nbsp;</th>
		    <th align="left">&nbsp;Retail Price&nbsp;</th>
		</tr>
		
	    <c:forEach var="row" items="${p.data}">   
		    <tr><td align="left">${row.prod_id}</td> 
		        <td align="left">${row.prod_desc}</td>  
		        <td align="right">${row.formattedCost}</td>  
		        <td align="right">${row.formattedPrice}</td> 
		    </tr>  
	    </c:forEach>	    
	    </table>
	    <%= p.getError_msg() %>
	</div>
</div>
</body>
</html>