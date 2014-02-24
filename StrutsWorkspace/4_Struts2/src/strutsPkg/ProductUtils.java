package strutsPkg;
import java.io.Serializable;
import java.sql.*;
import java.text.*;
import java.util.*;

public class ProductUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String error_msg;
	private String msg;
	private Connection cn = null;
	private ArrayList<Product> data;
	
	public ProductUtils() {
		System.out.println("in constructor this id is: \t" + this);
	}	
	public String insert() {
		System.out.println("runInsert obj id is:     \t" + this);
		this.error_msg = "Insert Succeeded";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, this.prod_desc);
			pstmt.setString(2, Float.toString(this.prod_cost));
			pstmt.setString(3, Float.toString(this.prod_price));
			pstmt.execute(); 
			this.msg = "Added " + this.prod_desc;
		} catch (Exception e) {
			this.error_msg = e.getMessage();			
			return "error";
		}
		System.out.println("returning from runInsert");
		return "success";
	}
	public String delete() {
		this.error_msg = "Delete Succeeded";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("DELETE FROM candy_product WHERE prod_id = ?");
			pstmt.setLong(1, this.prod_id);
			pstmt.execute(); 			
		} catch (Exception e) {
			this.error_msg = e.getMessage();			
			return "error";
		}
		return "success";
	}	
	public String retrieveProductInformation() {
		System.out.println("retrieveProductInformation obj id is:  " + this);
		
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(cn == null || cn.isClosed()) {
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
						                       "STUDENT", "$5333");
			}
			String query = "SELECT prod_id, prod_desc, prod_cost, prod_price " +
			               "FROM candy_product ORDER BY prod_desc";			               	
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Product p = new Product();
				p.setProd_id(rs.getLong("prod_id"));
				p.setProd_desc(rs.getString("prod_desc"));
				p.setProd_cost(rs.getFloat("prod_cost"));
				p.setProd_price(rs.getFloat("prod_price"));
				p.setFormattedCost(f.format(p.getProd_cost()));
				p.setFormattedPrice(f.format(p.getProd_price()));
				this.data.add(p);
			}
		} catch (Exception e) {
			this.error_msg = e.getMessage();
			return "error";
		}
		return "success";
	}
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
	public String getError_msg() {
		System.out.println("getError_Msg() obj id is: \t" + this);
		return error_msg;
	}
	public void setError_msg(String errorMsg) {
		error_msg = errorMsg;
	}
	public ArrayList<Product> getData() {
		System.out.println("getData obj() id is:       \t" + this);
		return data;
	}
	public Connection getCn() {
		return cn;
	}
	public void setCn(Connection cn) {
		this.cn = cn;
	}
	public String getMsg() {
		System.out.println("getMsg() obj id is:       \t" + this);
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
