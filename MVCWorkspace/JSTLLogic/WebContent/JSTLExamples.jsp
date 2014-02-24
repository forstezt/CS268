<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<title>JSTLLogic</title>
</head>
<body>
	<c:set var="foo" scope="page" value="Mike" />
	${foo}<br />
	<c:set var="foo" scope="page" value="Joline" />
	${foo}<br />
	<c:if test="${foo =='Joline'}">foo is Joline<br /></c:if>
	<c:set var="foo" scope="page" value="No one" />
	<c:choose>
		<c:when test="${foo == 'Mike'}">
			foo is Mike<br />
		</c:when>
		<c:when test="${foo == 'Joline'}">
			foo is Joline<br />
		</c:when>
		<c:otherwise>
			foo is ${foo}<br />
		</c:otherwise>
	</c:choose>
</body>
</html>