package edu.uwec.cs.morriscm;
import java.io.Serializable;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class DataUtilities implements Serializable {
	private static final long serialVersionUID = 1L;

	public void insert(HttpServletRequest request) {
		String msg = "";			         
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
		    msg = "Added " + request.getParameter("prod_desc");
		} catch (Exception e) {
			msg = e.getMessage();			
		}
		request.setAttribute("msg", msg);
	}
	public void delete(HttpServletRequest request) {
		String msg = "";			         
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
		    msg = "Deleted " + request.getParameter("prod_desc");
		} catch (Exception e) {
			msg = e.getMessage();			
		}
		request.setAttribute("msg", msg);
	}	
	public void update(HttpServletRequest request) {
		String msg = "";			         
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
			pstmt.execute();

			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			msg = e.getMessage();
		}
		request.setAttribute("msg", msg);
	}
	public void retrieveProductInformation(HttpServletRequest request) {
		ArrayList<Product> data = new ArrayList<Product>();
		String error_msg = "";
		NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product ORDER BY prod_desc";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				Product p = new Product();
				p.setProd_id(rs.getLong("prod_id"));
				p.setProd_desc(rs.getString("prod_desc"));
				p.setEscProd_desc(rs.getString("prod_desc").replace("'", "\\'"));
				p.setProd_cost(rs.getFloat("prod_cost"));
				p.setProd_price(rs.getFloat("prod_price"));
				p.setFormattedCost(f.format(p.getProd_cost()));
				p.setFormattedPrice(f.format(p.getProd_price()));
				data.add(p);
			}
			//Clean up resources
		    cn.close();
		} catch (Exception e) {
			error_msg = e.getMessage();
			if(request.getAttribute("msg") != null) {
				error_msg += "\n" + request.getAttribute("msg");
			}
			request.setAttribute("msg", error_msg);
		}	
		request.setAttribute("data", data);		
	}
	
	public void retrieveSingleProduct(HttpServletRequest request) {
		String msg = "";
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
			msg = e.getMessage();
		}	
		// store to allow view to access product properties
		request.setAttribute("product", p);	
		request.setAttribute("msg", msg);
	}
}
