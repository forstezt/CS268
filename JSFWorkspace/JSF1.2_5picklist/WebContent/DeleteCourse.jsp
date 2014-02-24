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
		<h:dataTable value="#{courseutils.courseList}" 
		             var="row"
		             rowClasses="oddRow, evenRow" 
		             columnClasses="colLeft,colLeft,colLeft,colRight">
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Department&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.d_name}"/>
			</h:column>				             
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Call ID&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.c_callid}" />
			</h:column>
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Name&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.c_name}"/>
			</h:column>
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Credits&nbsp;</h:outputText>
				</f:facet>
				<h:outputText value="#{row.c_credits}"/>
			</h:column>				
			<h:column headerClass="columnHeader">
       			<h:commandButton value="Delete" action="#{courseutils.delete}">
   					<f:setPropertyActionListener value="#{row}" target="#{courseutils.c}" />       			
       			</h:commandButton>
       		</h:column>			
		</h:dataTable>
		</h:form>
		<br />	
		<center><h:outputLabel value="#{courseutils.msg}" /></center>
	</div>
</div>		
</body>
</html>
</f:view>
 