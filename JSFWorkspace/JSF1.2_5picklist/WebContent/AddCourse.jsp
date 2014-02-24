<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Add Course</title>
</head>
<body>
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent" class="datatable">
		<h:form id="frmDepartment" rendered="true">
			<table>
			<tr><td><h:outputLabel value="Call ID" /></td>
			    <td><h:inputText id="c_callid" rendered="true" 
			    	             required="true" value="#{courseutils.c.c_callid}">
			    		<f:validateLength minimum="1" maximum="50" />
                 		<f:attribute name="requiredMessage" value="Call ID is required"/>			    		
 			    	</h:inputText>	
			    	<h:message for="c_callid" styleClass="red" />		    
			    </td>
			</tr>
			
			<tr><td><h:outputLabel value="Name" /></td>
			    <td><h:inputText id="c_name" rendered="true" 
			    	             required="true" value="#{courseutils.c.c_name}">
			    		<f:validateLength minimum="1" maximum="50" />
                 		<f:attribute name="requiredMessage" value="Name is required"/>			    		
 			    	</h:inputText>	
			    	<h:message for="c_name" styleClass="red" />		    
			    </td>
			</tr>
			
			<tr><td><h:outputLabel value="Credits" /></td>
			    <td><h:inputText id="c_credits" rendered="true" 
			    	             required="true" value="#{courseutils.c.c_credits}">
			    		<f:validateLongRange maximum="15" minimum="0" />
                 		<f:attribute name="requiredMessage" value="Credit is required"/>			    		
 			    	</h:inputText>	
			    	<h:message for="c_credits" styleClass="red" />		    
			    </td>
			</tr>			
						
			<tr><td><h:outputLabel value="Department" /></td>
			    <td><h:selectOneListbox id="d_id" 
			    						value="#{courseutils.c.d_id}"
			                            required="true"
			                            size="3">
			    		<f:selectItems value="#{courseutils.departmentList}" />
                 		<f:attribute name="requiredMessage" value="Department is required"/>			    		
			        </h:selectOneListbox>
			    	<h:message for="d_id" styleClass="red" />		    
			    </td>
			</tr>
		
			<tr><td>&nbsp;</td>
			    <td><h:commandButton value="submit" action="#{courseutils.insert}" /></td>
			</tr>			
		    <tr><td colspan="2" align="right"><h:outputLabel value="#{courseutils.msg}" /></td></tr>
		    </table>			
		</h:form>
	</div>
</div>
</body>
</html>
</f:view>