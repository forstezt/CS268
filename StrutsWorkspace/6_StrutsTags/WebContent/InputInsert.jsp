<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix='s' uri='/struts-tags' %>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Insert Product</title>
</head>
<body>
<s:form key="frmInsert" action="insert" cssClass="dataTable">
  <tr><th colspan="2" align="center">Add a New Product</th></tr>
  <tr><td>Description</td><td><input type="text" name="prod_desc" /></td></tr>
  <s:textfield key="prod_cost" label="Our Cost" />
  <s:textfield key="prod_price" label="Retail Price" />
  <s:submit value="Submit"/>
</s:form>
</body>
</html>
