<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ironwood University</title>
<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#cccccc" onLoad="formLoad();">
<script type="text/javascript" src="JSLibrary/login.js"></script>
<script type="text/javascript" src="JSLibrary/buttons.js"></script>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
        <form name="frmLogin" action="processLogin.jsp">
        <table class="dataTable" width="300px">
            <tr><td align="right">UserID:&nbsp;</td><td><input type="text" name="f_id" size="20"></td></tr>
            <tr><td align="right">PIN:&nbsp;</td><td><input type="password" name="f_pin" size="20"></td></tr>
            <tr><td>&nbsp;</td><td align="left"><input type="submit" value="Login" onClick="return validate();"></td></tr>
            <tr><td colspan="2">&nbsp;</td></tr>
            <tr><td colspan="2" align="left">Valid Logins (for testing purposes):</td></tr>
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
        </form>
    </div>    
</div>
</body></html>
