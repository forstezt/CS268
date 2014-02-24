<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>                      
<html>
<head>
	<title>Error Page</title>
  	<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent" class="datatable">
    	<div align="left">
		<h2>An error occurred preventing the last action from completing</h2><br /><br />
		<h:outputLabel value="#{courseutils.msg}" />
		</div>
	</div>
</div>
</body>
</html>
</f:view>