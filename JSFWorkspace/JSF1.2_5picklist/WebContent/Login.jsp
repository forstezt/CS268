<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<f:view>                      
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Login</title>
</head>
<body bgcolor="#cccccc">
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent">
    	<h:form id="Login" styleClass="datatable">
			<table>
			<tr><td><h:outputLabel value="User ID" /></td>
			    <td><h:inputText id="f_userid" rendered="true" 
			                     required="true" value="#{loginutils.f_userid}">
			    		<f:validateLength minimum="2" maximum="10" />
                 		<f:attribute name="requiredMessage" value="User ID is required"/>
 			    	</h:inputText>
			    	<h:message for="f_userid" styleClass="red" />
			    </td>
			</tr>
			<tr><td><h:outputLabel value="Password" /></td>		
			    <td><h:inputSecret id="f_pin" rendered="true" 
			                       required="true" value="#{loginutils.f_pin}">
			    		<f:validateLength minimum="2" maximum="10" />
			    		<f:validateLongRange maximum="9999999999" />
                 		<f:attribute name="requiredMessage" value="PIN is required"/>
  			    	</h:inputSecret>
			    	<h:message for="f_pin" styleClass="red" />			    	
			    </td>
			</tr>		
			<tr><td>&nbsp;</td>
			    <td><h:commandButton value="submit" action="#{loginutils.validateLogin}" /></td>
			</tr>
            <tr><td>&nbsp;</td><td><span class="error">&nbsp;<h:outputLabel value="#{loginutils.login_msg}" /></span>&nbsp;</td></tr>
            <tr>
                <td><b>UserID</b></td>
                <td><b>PIN</b></td>
            </tr>
            <tr>
                <td>coxk</td>
                <td>1181</td>
            </tr>
            <tr>
                <td>blanj</td>
                <td>1075</td>
            </tr>
            <tr>
                <td>willj</td>
                <td>8531</td>
            </tr>
            <tr>
                <td>shenl</td>
                <td>1690</td>
            </tr>
            <tr>
                <td>browp</td>
                <td>9899</td>
            </tr>
            <tr>
                <td>whitj</td>
                <td>3214</td>
            </tr>	
		    </table>
    	</h:form>
    </div>    
</div>
</body></html>
</f:view>
