package strutsPkg;
import java.io.Serializable;
import java.sql.*;
import java.text.*;
import java.util.*;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Product implements Serializable, SessionAware {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private ArrayList<Product> data;
	private Connection cn = null;
	private SessionMap<String, Object> session = null;

	public String clearDelete_msg() {
		session.remove("delete_msg");
		return "success";
	}	
	public String runInsert() {
		session.put("insert_msg", "Insert Succeeded");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, this.prod_desc);
			pstmt.setString(2, Float.toString(this.prod_cost));
			pstmt.setString(3, Float.toString(this.prod_price));
			pstmt.execute(); 			
		} catch (Exception e) {
			session.put("insert_msg", e.getMessage());
			return "error";
		}
		return "success";
	}
	public String runDelete() {
		if(this.prod_id == 0) {
			session.put("delete_msg", "Select a product before clicking the delete button");			
		} else {
			session.put("delete_msg", "Delete Succeeded");			
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("DELETE FROM candy_product WHERE prod_id = ?");
			pstmt.setLong(1, this.prod_id);
			pstmt.execute(); 			
		} catch (Exception e) {
			session.put("delete_msg", e.getMessage());			
			return "error";
		}
		return "success";
	}	
	public String retrieveProductInformation() {
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(cn == null || cn.isClosed()) {
				cn=DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			}
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
			session.put("error_msg", e.getMessage());
			return "error";
		}
		return "success";
	}
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
	public String getFormattedCost() {
		return formattedCost;
	}
	public void setFormattedCost(String formattedCost) {
		this.formattedCost = formattedCost;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public ArrayList<Product> getData() {
		return data;
	}
	@SuppressWarnings("unchecked")
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;		
	}
}
