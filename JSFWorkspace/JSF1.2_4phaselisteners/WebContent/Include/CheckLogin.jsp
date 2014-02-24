<%
	if(session.getAttribute("cust_id") == null) {
		response.sendRedirect("Login.jsf");
	}
%>