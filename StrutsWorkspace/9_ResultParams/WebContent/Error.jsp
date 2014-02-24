<!DOCTYPE html>
<html>
<head>
	<title>Error Page</title>
  	<link href="CSS/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="bodyCenter">
	<%@ include file="IncludeLibrary/NavBar.htm" %>
    <div id="maincontent">
	<h2>An error occurred preventing the last action from completing</h2><br /><br />
	${msg}
	</div>
</div>
</body>
</html>