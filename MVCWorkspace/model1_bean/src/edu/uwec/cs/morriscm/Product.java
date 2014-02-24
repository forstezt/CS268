package edu.uwec.cs.morriscm;
import java.io.Serializable;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	// Note - private variables can accessed from a session variable
	//        in a JSP page using the JSP Expression Language ${variable name}
	//        only if you've created "getters" for them
	private ArrayList<Product> data;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private String error_msg;

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
	public ArrayList<Product> getData() {
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
	public void retrieveProductInformation() {
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		Connection cn = null;
		this.error_msg = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product ORDER BY prod_desc";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				Product p = new Product();
				p.prod_id = rs.getLong("prod_id");
				p.prod_desc = rs.getString("prod_desc");
				this.prod_desc = p.prod_desc;
				p.prod_cost = rs.getFloat("prod_cost");
				p.prod_price = rs.getFloat("prod_price");
				p.formattedCost = f.format(p.prod_cost);
				p.formattedPrice = f.format(p.prod_price);
				this.data.add(p);
			}
		} catch (ClassNotFoundException e) {
			this.error_msg = e.getMessage();			
		} catch (SQLException e) {
			this.error_msg = e.getMessage();			
		}	
		// close the connection
	    try { 
	    	cn.close(); 
	    } catch (SQLException e) { 
	    	e.printStackTrace(); 
	    }
		// allow a JSP page access to the data collection's properties
		//request.setAttribute("product", this);	
	}
}
