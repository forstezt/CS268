<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.sql.*" %>
<html>
<head><title>Product Insert</title></head>
<body>
<%// add the library with database functionality
  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
  // create the connection object (use your account and password)
  Connection cn = 
	  DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
		                          "STUDENT", "$5333");
  //create the prepared statement
  try {
    PreparedStatement pstmt = cn.prepareStatement("INSERT INTO candy_product " +
  		                                          "(prod_desc, prod_cost, " + 
  		                                          " prod_price) " + 
	  	                                          "VALUES (?, ?, ?)");
    pstmt.setString(1, request.getParameter("prod_desc"));
    pstmt.setString(2, request.getParameter("prod_cost"));
    pstmt.setString(3, request.getParameter("prod_price"));
    pstmt.execute(); 
	response.sendRedirect("DisplayProduct.jsp");
  } catch (Exception e) { %>
  Server error: <%= e.getMessage() %>
<%} finally {
	cn.close(); 
  }%>
</body>
</html>
