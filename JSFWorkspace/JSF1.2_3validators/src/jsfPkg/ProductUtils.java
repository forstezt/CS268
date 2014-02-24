package jsfPkg;
import java.io.Serializable;
import java.sql.*;
import java.text.*;
import java.util.*;

public class ProductUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product p = null;
	private String msg;
	private ArrayList<Product> productlist = null;
	
	public ProductUtils() {
		super();
		if(p == null) {
			p = new Product();
		}
		if(productlist == null) {
			retrieveProducts();
		}		
	}
	public String insert() {
		MySQL.open();
		try {
		    PreparedStatement pstmt = 
		    	MySQL.cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                          "VALUES (?, ?, ?)");
			pstmt.setString(1, this.p.getProd_desc());
			pstmt.setString(2, this.p.getProd_cost());
			pstmt.setString(3, this.p.getProd_price());
			pstmt.execute(); 			
			this.msg = "Add Succeeded";
		} catch (Exception e) {
			this.msg = e.getMessage();
			MySQL.close();
			return "error";
		}
		retrieveProducts();		
		MySQL.close();
		return "success";
	}
	public String update() {
		MySQL.open();
		String sqlQuery = "UPDATE candy_product " +
		                  "SET prod_desc = ?," +
		                      "prod_cost = ?," +
		                      "prod_price = ? " +
				          "WHERE prod_id = ?;";
		
		try {
			// interate through the arraylist
			for(Product p : productlist) {
				if(p.isModified()) {
					PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
					pstmt.setString(1, p.getProd_desc());
					pstmt.setString(2, p.getProd_cost());
					pstmt.setString(3, p.getProd_price());
					pstmt.setString(4, p.getProd_id());
					p.setEditable(false);
					p.setModified(false);
					pstmt.execute(); 
				}
			}				
			this.msg = "Updated Products";
		} catch (SQLException e) {
			this.msg = e.getMessage();
			return "error";
		}
		retrieveProducts();
	    MySQL.close();
	    return "success";
	}	
	public String delete() {
		MySQL.open();
		try {		
		    PreparedStatement pstmt = 
		    	MySQL.cn.prepareStatement("DELETE FROM candy_product WHERE prod_id = ?");
		    
			pstmt.setString(1, p.getProd_id());
			pstmt.execute(); 			
			this.msg = "Delete Succeeded";
		} catch (Exception e) {
			this.msg = e.getMessage();			
			return "error";
		}
		retrieveProducts();
		MySQL.close();
		return "success";
	}	
	private String retrieveProducts() {
		this.productlist = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		MySQL.open();
		try {
			String query = "SELECT prod_id, prod_desc, prod_cost, prod_price " +
			               "FROM candy_product ORDER BY prod_desc";			               	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Product p = new Product();
				p.setProd_id(rs.getString("prod_id"));
				p.setProd_desc(rs.getString("prod_desc"));
				p.setProd_cost(rs.getString("prod_cost"));
				p.setProd_price(rs.getString("prod_price"));
				p.setFormattedCost(f.format(Float.parseFloat(p.getProd_cost())));
				p.setFormattedPrice(f.format(Float.parseFloat(p.getProd_price())));
				this.productlist.add(p);
			}
		} catch (Exception e) {
			this.msg = e.getMessage();
		}
		MySQL.close();
		return "success";
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public String getMsg() {
		String m = msg;
		msg = "";
		return m;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<Product> getProductlist() {
		return productlist;
	}
}
