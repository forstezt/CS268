<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.sql.*,java.text.*" %>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Display Product</title>
</head>
<body>
<%// add the library with database functionality
  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
  Connection cn = 
	  DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT",
			                      "STUDENT", "$5333");
  Statement stmt = cn.createStatement();
  String query = "SELECT prod_desc, prod_cost, prod_price " +
                 "FROM candy_product";
  try {
    // create the result set object
	ResultSet rs = stmt.executeQuery(query); 
	NumberFormat f = NumberFormat.getCurrencyInstance();
%>
   <table class="dataTable">
     <tr><th>Product</th><th>Cost</th><th>Price</th></tr>
<% while (rs.next()) { %>
	 <tr>
	   <td align="left"><%=rs.getString("prod_desc") %></td>
	   <td align="right"><%=f.format(rs.getFloat("prod_cost")) %></td>
	   <td align="right"><%=f.format(rs.getFloat("prod_price")) %></td>
	 </tr>
<% } %>
     <tr>
       <td align="left">
         <input type="button" value="Add product" 
                onclick="location.href='InputInsert.jsp';" />
       </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
   </table>
<% } catch (Exception e) { %>
     Server error: <%= e.getMessage() %>
<% } finally { cn.close(); } %>
</body>
</html>
 