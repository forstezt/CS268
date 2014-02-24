<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><title>Login</title></head>
<body>
<% 
	String user = request.getParameter("f_id");
	String pin = request.getParameter("f_pin");

	Class.forName("com.mysql.jdbc.Driver");
	Connection cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "S5333");

    String sqlQuery = 
    	"SELECT f_id FROM nw_faculty " +
    	"WHERE  f_userid=? AND f_pin=?";
    
	PreparedStatement pStmt = cn.prepareStatement(sqlQuery);
	
	pStmt.setString(1, user);
	pStmt.setInt(2, Integer.parseInt(pin));
	
	ResultSet rs = 	pStmt.executeQuery();
	if (rs.next()) {
	    Cookie c1 = new Cookie ("f_id", rs.getString("f_id"));
	    response.addCookie(c1);
	    response.sendRedirect("displayCourses.jsp");
	} else {
		// make sure to remove any residual InstructorID cookie:
	    Cookie c1 = new Cookie ("f_id", "");
		c1.setMaxAge(0);
	    response.addCookie(c1);
	}
	//Clean up resources
	cn.close();
%>
	<center><h2>Please enter a valid login</h2></center>
</body>
</html>
