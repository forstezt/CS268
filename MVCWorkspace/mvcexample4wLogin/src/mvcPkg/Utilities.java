package mvcPkg;
import java.io.Serializable;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

public class Utilities implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public void insert(HttpServletRequest request) {
		String msg = "";
		MySQL ms = new MySQL();
		String errorOpeningMsg = ms.open();
		if(errorOpeningMsg.equals("")) {
			try {
				// assign -1 or null to any parameter that isn't sent
				String prod_desc = request.getParameter("prod_desc");  // assigns null if not found
				Float prod_cost = (request.getParameter("prod_cost") != null) ? Float.parseFloat(request.getParameter("prod_cost")) : -1;
				Float prod_price = (request.getParameter("prod_price") != null) ? Float.parseFloat(request.getParameter("prod_price")) : -1;
				//create the query statement
				String sqlQuery = "";
				PreparedStatement pstmt = null;
				sqlQuery = "INSERT INTO candy_product (prod_desc, prod_cost, prod_price)VALUES(?,?,?);";
				pstmt = ms.cn.prepareStatement(sqlQuery);
				pstmt.setString(1, prod_desc);
				pstmt.setFloat(2, prod_cost);
				pstmt.setFloat(3, prod_price);
				msg = "Added " + prod_desc;
				// execute the query
				pstmt.execute();		
			} catch (Exception e) {
				msg = e.getMessage();			
			}
		} else {
			msg = errorOpeningMsg;
		}
		ms.close();
		request.setAttribute("msg", msg);
	}	
	public void update(HttpServletRequest request) {
		String msg = "";
		MySQL ms = new MySQL();
		String errorOpeningMsg = ms.open();
		if(errorOpeningMsg.equals("")) {
			try {
				// assign -1 or null to any parameter that isn't sent
				Long prod_id = (request.getParameter("prod_id") != null) ? Long.parseLong(request.getParameter("prod_id")) : -1;
				String prod_desc = request.getParameter("prod_desc");  // assigns null if not found
				Float prod_cost = (request.getParameter("prod_cost") != null) ? Float.parseFloat(request.getParameter("prod_cost")) : -1;
				Float prod_price = (request.getParameter("prod_price") != null) ? Float.parseFloat(request.getParameter("prod_price")) : -1;
				//create the query statement
				String sqlQuery = "";
				PreparedStatement pstmt = null;
	
				sqlQuery = "UPDATE candy_product SET prod_desc = ?, prod_cost = ?, prod_price = ? WHERE prod_id = ?;";				
				pstmt = ms.cn.prepareStatement(sqlQuery);
				pstmt.setString(1, prod_desc);
				pstmt.setFloat(2, prod_cost);
				pstmt.setFloat(3, prod_price);
				pstmt.setLong(4, prod_id);			
				msg = "Updated " + prod_desc;
				// execute the query
				pstmt.execute();		
			} catch (Exception e) {
				msg = e.getMessage();			
			} 
		} else {
			msg = errorOpeningMsg;
		}
		ms.close();
		request.setAttribute("msg", msg);
	}	
	public void delete(HttpServletRequest request) {
		String msg = "";
		MySQL ms = new MySQL();
		String errorOpeningMsg = ms.open();
		if(errorOpeningMsg.equals("")) {
			try {
				// assign -1 or null to any parameter that isn't sent
				Long prod_id = (request.getParameter("prod_id") != null) ? Long.parseLong(request.getParameter("prod_id")) : -1;
				String prod_desc = request.getParameter("prod_desc");  // assigns null if not found
				//create the query statement
				String sqlQuery = "";
				PreparedStatement pstmt = null;
				sqlQuery = "DELETE FROM candy_product WHERE prod_id = ?;";
				pstmt = ms.cn.prepareStatement(sqlQuery);
				pstmt.setLong(1, prod_id);
					
				// execute the query
				pstmt.execute();
				msg = "Deleted " + prod_desc;
			} catch (Exception e) {
				msg = e.getMessage();			
			} 
		} else {
			msg = errorOpeningMsg;
		}
		ms.close();
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
	
	public void retrieveSingleProduct(Product product, HttpServletRequest request) {
		String msg = "";
		NumberFormat f = NumberFormat.getCurrencyInstance();
		MySQL ms = new MySQL();
		String errorOpeningMsg = ms.open();
		if(errorOpeningMsg.equals("")) {
			try {
				String sqlQuery = "";
				sqlQuery = "SELECT prod_id, prod_desc, prod_cost, prod_price FROM candy_product WHERE prod_id=" + request.getParameter("prod_id");
				Statement stmt = ms.cn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);
				if(rs.next()) {
					product.setProd_id(Long.parseLong(request.getParameter("prod_id")));
					product.setProd_desc(rs.getString("prod_desc"));
					product.setProd_cost(rs.getFloat("prod_cost"));
					product.setProd_price(rs.getFloat("prod_price"));
					product.setFormattedCost(f.format(product.getProd_cost()));
					product.setFormattedPrice(f.format(product.getProd_price()));
					request.setAttribute("data", product);
				}
				//Clean up resources
			    ms.cn.close();
			} catch (Exception e) {
				msg = e.getMessage();			
			}	
		} else {
			msg = errorOpeningMsg;
		}
		request.setAttribute("msg", msg);
	}
}
