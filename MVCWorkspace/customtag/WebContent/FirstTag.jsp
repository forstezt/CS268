<!DOCTYPE html>
<html>
<head>
	<title>Your first JSP tag : FirstTag</title>
</head>
<body>
<%@ taglib uri="/WEB-INF/tlds/DemoTags.tld" prefix="ft" %>

<p align="center"><em><u>Your first JSP tag : FirstTag</u></em></p>
<p>Name and address entered : <ft:firsttag name="Mike Morrison" address="Phillips 135"/></p>
<p>Name and/or address wasn't entered : <ft:firsttag /></p>

</body>
</html>
<!-- 
Explanation
We use the taglib directive to tell the application server we will be using a JSP tag in our JSP page. 
There are two attributes to taglib directive; uri and prefix. We set the uri attribute to the local 
address of DemoTags.tld TLD file. prefix attribute asks about the prefix we are going to use for this 
tag library, I chose "star".

&gt;%@ taglib uri="/WEB-INF/tlds/DemoTags.tld" prefix="star" %&lt;
Then we use the tag twice in the JSP page, first time we enter our name in the name attribute for the 
tag. Second time we don't enter anything in the name attribute.

<p>Name entered : <star:firsttag name="Faisal Khan" /></p>

<p>No name entered : <star:firsttag /></p>
Ok we are done with FirstTag.jsp JSP page. Now run using a URL like http://localhost:8080/web/jsp/FirstTag.jsp . 
To see the online demo click here.

You will notice that the tag gives different response depending on value of the "name" attribute.
-->