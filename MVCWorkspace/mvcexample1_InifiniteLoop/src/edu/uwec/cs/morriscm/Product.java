package edu.uwec.cs.morriscm;
import java.io.Serializable;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String error_msg;
	private String data;
	
	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public float getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(float prod_cost) {
		this.prod_cost = prod_cost;		
	}
	public float getProd_price() {
		return prod_price;
	}
	public void setProd_price(float prod_price) {
		this.prod_price = prod_price;		
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getData() {
		return data;
	}
	public void modifyProduct(String InsertOrUpdateOrDelete) {
		this.error_msg = "<table class=\"dataTable\">" +
			             "<tr><td colspan=\"2\" align=\"center\">Success</td></tr></table>";			         
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			if(InsertOrUpdateOrDelete.toLowerCase().equals("insert")) {
				sqlQuery = "INSERT INTO candy_product (prod_desc, prod_cost, prod_price)VALUES(?,?,?);";
				executePStmt(sqlQuery, cn);
			} else if(InsertOrUpdateOrDelete.toLowerCase().equals("update")) {
				sqlQuery = "UPDATE candy_product SET prod_desc = ?, prod_cost = ?, prod_price = ? WHERE prod_id = " + prod_id + ";";				
				executePStmt(sqlQuery, cn);
			} else if(InsertOrUpdateOrDelete.toLowerCase().equals("delete")) {
				sqlQuery = "DELETE FROM candy_product WHERE prod_id = " + prod_id;
				Statement stmt = cn.createStatement();
				stmt.execute(sqlQuery);
				this.error_msg = "";
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			this.error_msg = "<table class=\"dataTable\">" +
							 "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}
	}
	private void executePStmt(String sqlQuery, Connection cn) throws SQLException {
		PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
		pstmt.setString(1, prod_desc);
		pstmt.setFloat(2, prod_cost);
		pstmt.setFloat(3, prod_price);
		// execute the query
		pstmt.execute();
	}
	public void runQuery(HttpServletRequest request, String action) {
		try {
			// assign -1 or null to any parameter that isn't sent
			this.prod_id = (request.getParameter("prod_id") != null) ? Long.parseLong(request.getParameter("prod_id")) : -1;
			this.prod_desc = request.getParameter("prod_desc");  // assigns null if not found
			this.prod_cost = (request.getParameter("prod_cost") != null) ? Float.parseFloat(request.getParameter("prod_cost")) : -1;
			this.prod_price = (request.getParameter("prod_price") != null) ? Float.parseFloat(request.getParameter("prod_price")) : -1;
			
			this.modifyProduct(action);
		} catch (Exception e) {
			this.error_msg = "<table class=\"dataTable\">" +
  			                 "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}
		// store to allow view to access product properties
		request.setAttribute("product", this);
	}
	public void retrieveProductInformation(HttpServletRequest request, String action) {
		this.data = "";
		NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product ORDER BY prod_desc";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			this.data += "<script type=\"text/javascript\">\n";
			if(action.equals("delete")) {
				this.data += "function deleteProduct(prod_id) {\n";
				this.data += "  window.location.href=\"delete.action?prod_id=\" + prod_id;\n";
			} else if(action.equals("update")) {
				this.data += "function updateProduct(prod_id) {\n";
				this.data += "  window.location.href=\"InputEdit.action?prod_id=\" + prod_id;\n";				
			}
			this.data += "}\n";
			this.data += "</script>\n";
			this.data += "<table class=\"dataTable\">\n";
			this.data += "<tr><th align=\"left\">&nbsp;Prod ID&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Description&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Our Cost&nbsp;</th>" +
			             "<th align=\"left\">&nbsp;Retail Price&nbsp;</th>" +
			             "<th>&nbsp;</th></tr>\n";
			while(rs.next()) {
				this.data += "<tr><td align=\"left\">" + rs.getString("prod_id") + "</td>\n" +
                "<td align=\"left\">" + rs.getString("prod_desc") + "</td>\n" +
                "<td align=\"right\">" + f.format(rs.getFloat("prod_cost")) + "</td>\n" +
                "<td align=\"right\">" + f.format(rs.getFloat("prod_price")) + "</td>\n";
				if(action.equals("delete")) {
				    this.data += "<td align=\"right\"><input type=\"button\" value=\"Delete\" " +
					             "onclick=deleteProduct(" + rs.getString("prod_id") + ")></td></tr>\n";
				} else if(action.equals("update")) {
				    this.data += "<td align=\"right\"><input type=\"button\" value=\"Edit\" " +
					             "onclick=updateProduct(" + rs.getString("prod_id") + ")></td></tr>\n";
				}
			}
			this.data += "</table>\n";
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			this.error_msg = "<table class=\"dataTable\">" +
			 "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}	
		// store to allow view to access product properties
		request.setAttribute("product", this);		
	}
	
	public void retrieveSingleProduct(HttpServletRequest request) {
		this.data = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product WHERE prod_id=" + request.getParameter("prod_id");
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				this.prod_id = Long.parseLong(request.getParameter("prod_id"));
				this.prod_desc = rs.getString("prod_desc");
				this.prod_cost = rs.getFloat("prod_cost");
				this.prod_price = rs.getFloat("prod_price");
				request.setAttribute("product", this);
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			this.error_msg = "<table class=\"dataTable\">" +
			 "<tr><td align=\"left\">Query failed:<br />" + e.getMessage() + "</td></tr></table>";			
		}	
		// store to allow view to access product properties
		request.setAttribute("product", this);		
	}
}
