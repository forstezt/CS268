<%@ include file="Include/CheckLogin.jsp" %>
<%@ include file="Include/PreventCaching.jsp" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Add a Product</title>
</head>
<script type="text/javascript" src="Includes/InputInsert.js"></script>
<body>
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent" class="datatable">
		<h:form id="frmProduct" rendered="true">
			<table>
			<tr><td><h:outputLabel value="Description" /></td>
			    <td>
			    	<h:inputText id="prod_desc" rendered="true" 
			    	             required="true" value="#{productutils.p.prod_desc}" >
			    		<f:validateLength minimum="2" maximum="50" />			    		
                 		<f:attribute name="requiredMessage" value="Description is required"/>			    		
 			    	</h:inputText>	
			    	<h:message for="prod_desc" styleClass="errorRed" />		    
			    </td>
			</tr>
			<tr><td><h:outputLabel value="Our Cost" /></td>
			    <td>
			    	<h:inputText id="prod_cost" rendered="true" 
			    	             required="true" value="#{productutils.p.prod_cost}">
			    		<f:validateDoubleRange maximum="1000" minimum="0.01" />
                		<f:attribute name="requiredMessage" value="Enter a value betwween .01 and 1000"/>	
			    	</h:inputText>
			    	<h:message for="prod_cost" styleClass="errorRed" />
			    </td>
			</tr>
			<tr><td><h:outputLabel value="Retail Price" /></td>
			    <td>
			    	<h:inputText id="prod_price" rendered="true" 
			    	             required="true" value="#{productutils.p.prod_price}">
			    		<f:validateDoubleRange maximum="1000" minimum="0.01" />			    		
               			<f:attribute name="requiredMessage" value="Enter a value betwween .01 and 1000"/>			    		
			    	</h:inputText>
		    		<h:message for="prod_price" styleClass="errorRed" />
			    </td>
			</tr>				
			<tr><td>&nbsp;</td>
			    <td><h:commandButton value="submit" action="#{productutils.insert}" /></td>
			</tr>
			</table>
    	</h:form>
    	<center><h:outputLabel value="#{productutils.msg}" /></center>		    
	</div>
</div>
</body>
</html>
</f:view>
