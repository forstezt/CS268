<%@ include file="Include/CheckLogin.jsp" %>
<%@ include file="Include/PreventCaching.jsp" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<f:view>                      
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body>
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent" class="datatable">
    	<h:form>
		<h:dataTable value="#{productutils.productlist}" 
		             binding="#{productutils.table}"
		             var="row"
		             rowClasses="oddRow,evenRow" 
		             columnClasses="colLeft, colRight, colRight">
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Product&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.prod_desc}" />
			</h:column>
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Our Cost&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.formattedCost}"/>
			</h:column>
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Retail Price&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.formattedPrice}"/>
			</h:column>	
			<h:column headerClass="columnHeader">
       			<h:commandButton value="Delete" action="#{productutils.delete}">
   					<f:setPropertyActionListener value="#{row}" target="" />       			
       			</h:commandButton>
       		</h:column>			
		</h:dataTable>
		</h:form>
		<br />	
		<center><h:outputLabel value="#{productutils.msg}" /></center>
	</div>
</div>		
</body>
</html>
</f:view>
 