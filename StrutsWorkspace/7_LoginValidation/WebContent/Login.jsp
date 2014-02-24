<!DOCTYPE html>
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
	<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
	<script src="JSLibrary/Login.js"></script>
	<title>Candy Products</title>
</head>
<body bgcolor="#cccccc" onload="formLoad();">
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
    	<!-- if the user has disabled javascript the validation will still occur when validatelogin is invoked from struts.xml -->
    	<s:form key="frmLogin" action="validatelogin" cssClass="dataTable" validate="true">
    		<s:textfield key="username" label="Username" />
    		<s:password key="password" label="Password" showPassword="true" />
    		<s:submit value="Login" />    		
    		
            <tr><td>&nbsp;</td><td><span class="error">&nbsp;${login_msg}</span>&nbsp;</td></tr>
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
    	</s:form>            	
    </div>    
</div>
</body></html>
