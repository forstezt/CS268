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
		             
            <h:column>
               <f:facet name="header">
                  <h:outputText value="Edit" style="font-weight: bold"/>
               </f:facet>
               <h:selectBooleanCheckbox value="#{row.editable}" onclick="submit()"/>
            </h:column>
 
 			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Department&nbsp;</h:outputText>
				</f:facet>
				<h:selectOneListbox value="#{row.d_id}"
			                        required="true"
			                        rendered="#{row.editable}"
			                        size="1">
				    <f:selectItems value="#{courseutils.departmentList}" />
	                <f:attribute name="requiredMessage" value="Department is required"/>			    		
			    </h:selectOneListbox>
			    <h:outputText value="#{row.d_name}" rendered="#{not row.editable}" />			    										
			</h:column>		
            
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Call ID&nbsp;</h:outputText>
				</f:facet>
				<h:inputText value="#{row.c_callid}" rendered="#{row.editable}" required="true"/>
				<h:outputText value="#{row.c_callid}" rendered="#{not row.editable}"/>
			</h:column>
			
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Name&nbsp;</h:outputText>
				</f:facet>
				<h:inputText value="#{row.c_name}" rendered="#{row.editable}" required="true"/>
				<h:outputText value="#{row.c_name}" rendered="#{not row.editable}"/>
			</h:column>
			
			<h:column headerClass="columnHeader">
				<f:facet name="header">
					<h:outputText>&nbsp;Credits&nbsp;</h:outputText>
				</f:facet>
				<h:inputText value="#{row.c_credits}" rendered="#{row.editable}" required="true"/>
				<h:outputText value="#{row.c_credits}" rendered="#{not row.editable}"/>
			</h:column>									             
			
		</h:dataTable>
        <h:commandButton value="Save Changes" action="#{courseutils.update}" /><br />	
        <h:messages styleClass="red"></h:messages>      
		</h:form>
 		<br />	
		<center><h:outputLabel value="#{courseutils.msg}" /></center>
	</div>
</div>		
</body>
</html>
</f:view>
 