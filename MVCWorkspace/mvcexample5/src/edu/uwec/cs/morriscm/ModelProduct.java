package edu.uwec.cs.morriscm;
import java.io.Serializable;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class ModelProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	// Note - private variables can accessed from a session variable
	//        in a JSP page using the JSP Expression Language ${variable name}
	//        only if you've created "getters" for them
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private String msg;
	private ArrayList<ModelProduct> data;
	
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
	public String getmsg() {
		return msg;
	}
	public void setmsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<ModelProduct> getData() {
		return data;
	}
	public String getFormattedCost() {
		return formattedCost;
	}
	public void setFormattedCost(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}		
	public void runQuery(HttpServletRequest request, String action) {
		this.msg = "Success";
		Connection cn = null;
		try {
			// assign -1 or null to any parameter that isn't sent
			prod_id = (request.getParameter("prod_id") != null) ? Long.parseLong(request.getParameter("prod_id")) : -1;
			prod_desc = request.getParameter("prod_desc");  // assigns null if not found
			prod_cost = (request.getParameter("prod_cost") != null) ? Float.parseFloat(request.getParameter("prod_cost")) : -1;
			prod_price = (request.getParameter("prod_price") != null) ? Float.parseFloat(request.getParameter("prod_price")) : -1;
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			PreparedStatement pstmt = null;
			if(action.toLowerCase().equals("insert")) {
				sqlQuery = "INSERT INTO candy_product (prod_desc, prod_cost, prod_price)VALUES(?,?,?);";
				pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setString(1, prod_desc);
				pstmt.setFloat(2, prod_cost);
				pstmt.setFloat(3, prod_price);
			} else if(action.toLowerCase().equals("update")) {
				sqlQuery = "UPDATE candy_product SET prod_desc = ?, prod_cost = ?, prod_price = ? WHERE prod_id = ?;";				
				pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setString(1, prod_desc);
				pstmt.setFloat(2, prod_cost);
				pstmt.setFloat(3, prod_price);
				pstmt.setLong(4, prod_id);			
			} else if(action.toLowerCase().equals("delete")) {
				sqlQuery = "DELETE FROM candy_product WHERE prod_id = ?;";
				pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setLong(1, prod_id);
			}
			// execute the query
			pstmt.execute();		
		} catch (Exception e) {
			this.msg = e.getMessage();			
		} finally {
		    try { cn.close(); } catch (SQLException e) {}
		}
	}	
	public void retrieveProductInformation(HttpServletRequest request) {
		this.data = new ArrayList<ModelProduct>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product ORDER BY prod_desc";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				ModelProduct p = new ModelProduct();
				p.prod_id = rs.getLong("prod_id");
				p.prod_desc = rs.getString("prod_desc");
				p.prod_cost = rs.getFloat("prod_cost");
				p.prod_price = rs.getFloat("prod_price");
				p.formattedCost = f.format(p.prod_cost);
				p.formattedPrice = f.format(p.prod_price);
				this.data.add(p);
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			this.msg = e.getMessage();			
		}	
	}	
	public void retrieveSingleProduct(HttpServletRequest request) {
		NumberFormat f = NumberFormat.getCurrencyInstance();
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
				this.formattedCost = f.format(this.prod_cost);
				this.formattedPrice = f.format(this.prod_price);
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			this.msg = e.getMessage();			
		}	
	}
}
