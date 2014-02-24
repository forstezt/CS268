<%@ page import="java.sql.*" %>
<%@ include file="JSPLibrary/preventCaching.jsp" %>
<%@ include file="JSPLibrary/CheckLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add Course</title>
<link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
</head>
<body onload=formLoad();>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "S5333");

	//create the query statement
	Statement stmt = cn.createStatement();
	String sqlQuery = "SELECT d_id, d_name FROM nw_department ORDER BY d_name";
	//create the recordset and execute the query
	ResultSet rs = 	stmt.executeQuery(sqlQuery);
%>
<script type="text/javascript" src="JSLibrary/courseForm.js"></script>
<div id="bodyCenter">
	<jsp:include page="IncludeLibrary/NavBar.htm" />
    <div id="maincontent">
		<form name=frmCourse action=insertCourse.jsp>
		<table class="dataTable">
			<tr>
				<td colspan=2 align="center" bgcolor="#808080"><font size=6 color="#FFFFFF"><b>Add a New Course</b></font></td>	
			</tr>
			<tr><td align=right>Call ID:&nbsp;</td><td><input type="text" name="c_callid"></td></tr>
			<tr><td align=right>Course Name:&nbsp;</td><td><input type="text" name="c_name"></td></tr>
			<tr><td align=right>Course Credits:&nbsp;</td><td><input type="text" name="c_credits"></td></tr>
			<tr><td align=right>Department:&nbsp;</td>
			    <td>
			    	<select size=3 name=d_id>
						<% while(rs.next()) { %>
							<option value=<%= rs.getString("d_id") %>><%= rs.getString("d_name") %></option>
						<% } %>
			    	</select>
			    </td>
			</tr>
			<tr><td>&nbsp;</td><td><input type=submit value="Submit Course" onclick="return validate();"></td></tr>
		</table>
		</form>
	</div>
</div>
<%
	//Clean up resources
	cn.close();
%>
</body>
</html>