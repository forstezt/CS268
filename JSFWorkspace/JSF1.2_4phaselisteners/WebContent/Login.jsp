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
    	<h:form styleClass="datatable">
			<table>
			<tr><td><h:outputLabel value="Username" /></td>
			    <td><h:inputText id="username" rendered="true" 
			                     required="true" value="#{loginutils.username}">
			    		<f:validateLength minimum="2" maximum="10" />
                 		<f:attribute name="RequiredMessage" value="Username is required"/>
 			    	</h:inputText>
			    	<h:message for="username" styleClass="errorRed" />
			    </td>
			</tr>
			<tr><td><h:outputLabel value="Password" /></td>		
			    <td><h:inputSecret id="password" rendered="true" 
			                       required="true" value="#{loginutils.password}">
			    		<f:validateLength minimum="2" maximum="10" />
                 		<f:attribute name="RequiredMessage" value="Password is required"/>
  			    	</h:inputSecret>
			    	<h:message for="password" styleClass="errorRed" />			    	
			    </td>
			</tr>		
			<tr><td>&nbsp;</td>
			    <td><h:commandButton value="submit" action="#{loginutils.validateLogin}" /></td>
			</tr>
           <tr><td>&nbsp;</td><td><span class="error">&nbsp;<h:outputLabel value="#{loginutils.login_msg}" /></span>&nbsp;</td></tr>
            <tr><td colspan="2" align="left">Valid Logins (for testing purposes):</td></tr>
            <tr>
                <td><b>username</b></td>
                <td><b>password</b></td>
            </tr>
            <tr>
                <td>jonesj</td>
                <td>1234</td>
            </tr>
            <tr>
                <td>swedburg</td>
                <td>2353</td>
            </tr>
            <tr>
                <td>pickpick</td>
                <td>5333</td>
            </tr>
            <tr>
                <td>kidcandy</td>
                <td>2351</td>
            </tr>
            <tr>
                <td>wateral</td>
                <td>8900</td>
            </tr>
            <tr>
                <td>bobbybon</td>
                <td>3011</td>
            </tr>	
			</table>
    	</h:form>
    </div>    
</div>
</body></html>
</f:view>
