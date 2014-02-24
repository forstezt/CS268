<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html>
<f:view>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Courses</title>
</head>
<body>
<div id="bodycenter">
	<%@ include file="Templates/maintemplate.htm" %>
	<div id="maincontent">
<h:form>
<!-- JavaScript validation doesn't work with a datatable - take a look at the generated names
     for each input when editing a row (view source) to see why this is a problem -->
	<h:dataTable value="#{courseutils.cData}"
	             var="row"
	             styleClass="datatable"
	             columnClasses="colLeft,colLeft,colLeft,colLeft,colLeft,colLeft">	             
        <h:column>
           	<h:commandButton rendered="#{row.editable}"     action="#{courseutils.updateCourse}"     value="Save"   onclick="return confirm('Save changes?');">
				<f:setPropertyActionListener value="#{row}" target="#{courseutils.c}" />       			           
           	</h:commandButton>
           	<h:outputLabel rendered="#{row.editable}">&nbsp;</h:outputLabel>
           	<h:commandButton rendered="#{row.editable}"     action="#{courseutils.setEditableFalse}" value="Cancel" />
           	
           	<h:commandButton rendered="#{not row.editable}" action="#{courseutils.setEditableTrue}"  value="Edit">
				<f:setPropertyActionListener value="#{row}" target="#{courseutils.c}" />       			                      	
           	</h:commandButton>
           	<h:outputLabel rendered="#{not row.editable}">&nbsp;</h:outputLabel>
			<h:commandButton rendered="#{not row.editable}" action="#{courseutils.deleteCourse}"     value="Delete" onclick="return confirm('Confirm delete?');">
				<f:setPropertyActionListener value="#{row}" target="#{courseutils.c}" />       			
			</h:commandButton>
		</h:column>
   		<h:column>
			<f:facet name="header"><h:outputText>Department</h:outputText></f:facet>
			<h:outputLabel styleClass="#{row.bgColorClass}" rendered="#{not row.editable and row.deptChanged}" value="#{row.d_name}" />
			<h:selectOneMenu id="d_id" value="#{row.d_id}" rendered="#{row.editable}">
			   	<f:selectItems value="#{courseutils.dData}" />
			</h:selectOneMenu>
    	</h:column>			
		<h:column>
			<f:facet name="header"><h:outputText value="Callid" /></f:facet>
			<h:outputLabel styleClass="#{row.bgColorClass}" value="#{row.c_callid}" rendered="#{not row.editable}" />
 			<h:inputText id="c_callid" value="#{row.c_callid}" rendered="#{row.editable}" required="true">
				<f:validateLength maximum="8" minimum="3" />
 			</h:inputText>
			<h:message for="c_callid" styleClass="error" />		
    	</h:column>	
 		<h:column>
			<f:facet name="header"><h:outputText>Name</h:outputText></f:facet>
			<h:outputLabel styleClass="#{row.bgColorClass}" value="#{row.c_name}" rendered="#{not row.editable}" />
 			<h:inputText id="c_name" value="#{row.c_name}" rendered="#{row.editable}" required="true">
 				<f:validateLength maximum="120" minimum="2" />
 			</h:inputText>
			<h:message for="c_name" styleClass="error" />		
     	</h:column>	
    	<h:column>
			<f:facet name="header"><h:outputText>Credits</h:outputText></f:facet>
			<h:outputLabel styleClass="#{row.bgColorClass}" value="#{row.c_credits}" rendered="#{not row.editable}" />
			<h:inputText id="c_credits" value="#{row.c_credits}" rendered="#{row.editable}" required="true">
				<f:validateLongRange minimum="0" maximum="10" />	
			</h:inputText>
			<h:message for="c_credits" styleClass="error" />		
    	</h:column> 
	</h:dataTable>
	<h:outputLabel value="#{courseutils.msg}" styleClass="blue" />	
</h:form>
<h:outputLabel value="#{courseutils.msg}" />			
</div>
</div>
</body>
</html>
</f:view>