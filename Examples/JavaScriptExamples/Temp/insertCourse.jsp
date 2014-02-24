<%@ page import="java.sql.*" %>
<%@ include file="JSPLibrary/preventCaching.jsp" %>
<%@ include file="JSPLibrary/CheckLogin.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><title>Insert Course</title></head>
<body>
<%
 	//create the connection
	Class.forName("com.mysql.jdbc.Driver");
	Connection cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "S5333");

	//create the query statement
	Statement stmt = cn.createStatement();
	String sqlQuery = "INSERT INTO nw_course (c_callid, c_name, c_credits, d_id) " +
	                  "VALUES('" + request.getParameter("c_callid") + "', '"  + request.getParameter("c_name") + "', " +
	                               request.getParameter("c_credits") + ", " + request.getParameter("d_id") + ")";
	// execute the query
	stmt.execute(sqlQuery);
	//Clean up resources
    cn.close();
	String added = request.getParameter("c_callid") + " - "  + request.getParameter("c_name") + " - " +
                   request.getParameter("c_credits");
	response.sendRedirect("displayCourses.jsp?added=" + added);
%>
</body>
</html>