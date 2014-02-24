<!DOCTYPE html>
<html>
<head>
<title>Versions</title>
</head>
<body>
	Servlet Engine:
	<%= session.getServletContext().getMajorVersion() %>.<%= session.getServletContext().getMinorVersion( )%>
	<br />
	
	JSP Engine:
	<%=JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion()%>
	<br />
	
	Application Server:
	<%=application.getServerInfo()%>
	<br />
	
	<a href='Versions'>See the same informtion from a servlet</a>
</body>
</html>