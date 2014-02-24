package edu.uwec.cs.morriscm;
import java.io.Serializable;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class DataUtilities implements Serializable {
	private static final long serialVersionUID = 1L;

	public void insert(HttpServletRequest request) {
		String error_msg = "<table class=\"dataTable\">" +
			               "<tr><td colspan=\"2\" align=\"center\">Success</td></tr></table>";			         
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "INSERT INTO candy_product (prod_desc, prod_cost, prod_price)VALUES(?,?,?);";
			PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
			pstmt.setString(1, request.getParameter("prod_desc"));
			pstmt.setFloat(2, Float.parseFloat(request.getParameter("prod_cost")));
			pstmt.setFloat(3, Float.parseFloat(request.getParameter("prod_price")));
			pstmt.execute();
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			error_msg = "<table class=\"dataTable\">" +
					    "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}
		request.setAttribute("error_msg", error_msg);
	}
	public void delete(HttpServletRequest request) {
		String error_msg = "<table class=\"dataTable\">" +
			               "<tr><td colspan=\"2\" align=\"center\">Success</td></tr></table>";			         
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "DELETE FROM candy_product WHERE prod_id = ?";
			PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
			pstmt.setLong(1, Long.parseLong(request.getParameter("prod_id")));
			pstmt.execute();
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			error_msg = "<table class=\"dataTable\">" +
			  		    "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}
		request.setAttribute("error_msg", error_msg);
	}	
	public void update(HttpServletRequest request) {
		String error_msg = "<table class=\"dataTable\">" +
			               "<tr><td colspan=\"2\" align=\"center\">Success</td></tr></table>";			         
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "UPDATE candy_product SET prod_desc = ?, prod_cost = ?, prod_price = ? WHERE prod_id = ?";				
			PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
			pstmt.setString(1, request.getParameter("prod_desc"));
			pstmt.setFloat(2, Float.parseFloat(request.getParameter("prod_cost")));
			pstmt.setFloat(3, Float.parseFloat(request.getParameter("prod_price")));
			pstmt.setLong(4, Long.parseLong(request.getParameter("prod_id")));

			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			error_msg = "<table class=\"dataTable\">" +
						"<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}
		request.setAttribute("error_msg", error_msg);
	}
	public void retrieveProductInformation(HttpServletRequest request, String action) {
		Product p = new Product();
		NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
		String data = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product ORDER BY prod_desc";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			data += "<script type=\"text/javascript\">\n";
			if(action.equals("delete")) {
				data += "function deleteProduct(prod_id) {\n";
				data += "  window.location.href=\"delete.action?prod_id=\" + prod_id;\n";
			} else if(action.equals("update")) {
				data += "function updateProduct(prod_id) {\n";
				data += "  window.location.href=\"InputEdit.action?prod_id=\" + prod_id;\n";				
			}
			data += "}\n";
			data += "</script>\n";
			data += "<table class=\"dataTable\">\n";
			data += "<tr><th align=\"left\">&nbsp;Prod ID&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Description&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Our Cost&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Retail Price&nbsp;</th>" +
			             "<th>&nbsp;</th></tr>\n";
			while(rs.next()) {
				data += "<tr><td align=\"left\">" + rs.getString("prod_id") + "</td>\n" +
                "<td align=\"left\">" + rs.getString("prod_desc") + "</td>\n" +
                "<td align=\"right\">" + f.format(rs.getFloat("prod_cost")) + "</td>\n" +
                "<td align=\"right\">" + f.format(rs.getFloat("prod_price")) + "</td>\n";
				if(action.equals("delete")) {
				    data += "<td align=\"right\"><input type=\"button\" value=\"Delete\" " +
					             "onclick=deleteProduct(" + rs.getString("prod_id") + ")></td></tr>\n";
				} else if(action.equals("update")) {
				    data += "<td align=\"right\"><input type=\"button\" value=\"Edit\" " +
					             "onclick=updateProduct(" + rs.getString("prod_id") + ")></td></tr>\n";
				}
			}
			data += "</table>\n";
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			String error_msg = "<table class=\"dataTable\">" +
			                   "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";
			request.setAttribute("error_msg", error_msg);
		}	
		// store to allow view to access product properties
		request.setAttribute("data", data);		
	}
	
	public void retrieveSingleProduct(HttpServletRequest request) {
		String error_msg = "";
		Product p = new Product();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product WHERE prod_id=" + request.getParameter("prod_id");
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				p.setProd_id(Long.parseLong(request.getParameter("prod_id")));
				p.setProd_desc(rs.getString("prod_desc"));
				p.setProd_cost(rs.getFloat("prod_cost"));
				p.setProd_price(rs.getFloat("prod_price"));
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			error_msg = "<table class=\"dataTable\">" +
			            "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}	
		// store to allow view to access product properties
		request.setAttribute("product", p);	
		request.setAttribute("error_msg", error_msg);
	}
}
