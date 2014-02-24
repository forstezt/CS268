<jsp:root version="2.0" 
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:f="http://java.sun.com/jsf/core" 
          xmlns:h="http://java.sun.com/jsf/html">      
<jsp:directive.page contentType="text/html"/>
<jsp:output omit-xml-declaration="no" 
            doctype-root-element="html"
            doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
            doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
<f:view>                      
<html>
<head>
  <title>Show Name</title>
</head>
<body>
    <h2><h:outputText value="Success page"/></h2>
   	<h:outputText value="Your name in uppercase is: "/>
   	<h:outputText value="#{WhatsYourName.name}" /><br />
   	<a href="nameForm.faces">Back to Name Form</a>
</body>
</html>
</f:view>
</jsp:root>