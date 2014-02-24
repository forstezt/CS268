<!DOCTYPE html>
<html>
<head>
	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="JSLibrary/login.js"></script>
	<title>Candy Store</title>
</head>
<body bgcolor="#cccccc" onload="formLoad();">
<div id="bodyCenter">
	<div id="maincontent">
        <form name="frmLogin" action="processlogin" method="post">
        <table class="dataTable" width="300px">
            <tr><td align="right">Username:&nbsp;</td><td><input type="text" name="username" size="20" /></td></tr>
            <tr><td align="right">Password:&nbsp;</td><td><input type="password" name="password" size="20" /></td></tr>
            <tr><td>&nbsp;</td><td align="left"><input type="submit" value="Login" onclick="return validate();" /></td></tr>
            <tr><td colspan="2" style="text-align:center;"><span class="invalidLogin">${msg}</span></td></tr>
            <tr><td colspan="2" align="left">Valid Logins (for testing purposes):</td></tr>
            <tr>
                <td><b>Username</b></td>
                <td><b>Password</b></td>
            </tr>
            <tr>
                <td>jonesj</td>
                <td>1234</td>
            </tr>
            <tr>
                <td>armstrong</td>
                <td>3333</td>
            </tr>
            <tr>
                <td>swedburg</td>
                <td>2353</td>
            </tr>
            <tr>
                <td>pickpick</td>
                <td>5333</td>
            </tr>
        </table>
        </form>
    </div>    
</div>
</body>
</html>
