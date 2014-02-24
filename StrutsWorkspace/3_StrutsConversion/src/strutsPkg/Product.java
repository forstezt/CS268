package strutsPkg;
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
	private String formattedCost;
	private String formattedPrice;
	private String error_msg;
	private ArrayList<Product> data;
	private Connection cn = null;
		
	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prodId) {
		prod_id = prodId;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prodDesc) {
		prod_desc = prodDesc;
	}
	public float getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(float prodCost) {
		prod_cost = prodCost;
	}
	public float getProd_price() {
		return prod_price;
	}
	public void setProd_price(float prodPrice) {
		prod_price = prodPrice;
	}
	public String getFormattedCost() {
		return formattedCost;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String msg) {
		error_msg = msg;
	}
	public ArrayList<Product> getData() {
		return data;
	}
	public String runInsert() {
		String returnValue = "success";
		this.error_msg = "Success";
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
					                         "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, prod_desc);
			pstmt.setFloat(2, prod_cost);
			pstmt.setFloat(3, prod_price);
			pstmt.execute(); 			
		} catch (Exception e) {
			this.error_msg = e.getMessage();	
			returnValue = "error";
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}
	public String retrieveProductInformation() {
		String returnValue = "success";
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
					                       "STUDENT", "$5333");
			String query = "SELECT prod_id, prod_desc, prod_cost, prod_price " +
			               "FROM candy_product ORDER BY prod_desc";			               	
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Product p = new Product();
				p.prod_id = rs.getLong("prod_id");
				p.prod_desc = rs.getString("prod_desc");
				p.prod_cost = rs.getFloat("prod_cost");
				p.prod_price = rs.getFloat("prod_price");
				p.formattedCost = f.format(p.prod_cost);
				p.formattedPrice = f.format(p.prod_price);
				this.data.add(p);
			}
		} catch (Exception e) {
			this.error_msg = e.getMessage();
			returnValue = "error";
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return returnValue;
	}
}
