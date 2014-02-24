<%@ page import="java.sql.*" %>
<%@ include file="JSPLibrary/preventCaching.jsp" %>
<%@ include file="JSPLibrary/CheckLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Display Category</title>
<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<% //create the connection
	Class.forName("com.mysql.jdbc.Driver");
	Connection cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "S5333");

	//create the query statement
	Statement stmt = cn.createStatement();
	String sqlQuery = "SELECT c_callid, c_name, c_credits, d_name " +
	                  "FROM nw_course INNER JOIN nw_department " +
	                  "ON nw_course.d_id = nw_department.d_id " +
	                  "ORDER BY d_name, c_callid";	
	//create the recordset and execute the query
	ResultSet rs = 	stmt.executeQuery(sqlQuery);
%>
<div id="bodyCenter">
	<jsp:include page="IncludeLibrary/NavBar.htm" />
    <div id="maincontent">
		<table class="dataTable">
		<tr><th>Department</th><th>Course</th><th>Title</th><th>Credits</th></tr>
		<%	String last_name = "";
			String d_name;
			String color = "#CCCCCC";
			while(rs.next()) { %>
			<% d_name = rs.getString("d_name"); %>
			<% if(!last_name.equals(d_name)) {
				    last_name = d_name;
			   		if(color == "#DDDCBF") {
			   			color = "#CCCCCC";
			   		} else {
						color = "#DDDCBF";
			   		} %>
				<tr bgcolor=<%= color %>>
				<td valign=top><b><%= d_name %></b></td>	 
			 <% } else { %>
				<tr bgcolor=<%= color %>>
				<td>&nbsp;</td>
			 <% } %>
				<td valign=top><b><%= rs.getString("c_callid") %></b></td>
				<td valign=top><b><%= rs.getString("c_name") %></b></td>
				<td valign=top><b><%= rs.getString("c_credits") %></b></td>
				</tr>
		<% } %>
		<%	String added = request.getParameter("added");			
			if(added != null) { %>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr><td colspan="4"><center>Successfully added: <%= added %></center></td></tr> 				
		<%	} %>
		</table>

    </div>
</div>
<%	//Clean up resources
	cn.close();
%>
</body>
</html>
