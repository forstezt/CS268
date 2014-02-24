package strutsPkg;
import java.io.Serializable;
import java.sql.*;

import org.apache.commons.io.output.NullWriter;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import java.text.*;
import java.util.*;

// note the SessionAware interface is implemented
// this allows access to the session from this class
// but you need to store the session object using the
// setSession method and access it as a Map object
// (you don't use the normal session methods)
public class ProductUtils implements Serializable, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private ArrayList<ProductUtils> data;
	private SessionMap<String, Object> session = null;
	private JDCConnectionBean cb = null;
	private Map<String, Object> application = null;
	
	public void setApplication(Map<String, Object> arg0) {
		application = (Map<String, Object>)arg0;
	}		
	public void setSession(Map<String, Object> arg0) {
		session = (SessionMap<String, Object>)arg0;
	}
	private Connection getCn() throws Exception {
		cb = (JDCConnectionBean)application.get("cnBean");
		if( cb == null) {
			cb = new JDCConnectionBean();
			application.put("cnBean", cb);
		}
		return cb.getConnection();
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
	public String getFormattedCost() {
		return formattedCost;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public ArrayList<ProductUtils> getData() {
		return data;
	}
	public String runInsert() {
		session.remove("error_msg");
		Connection cn = null;
		try {
			cn = this.getCn();			
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, prod_desc);
			pstmt.setFloat(2, prod_cost);
			pstmt.setFloat(3, prod_price);
			pstmt.execute(); 			
			session.put("msg", "Success");
		} catch (Exception e) {
			session.put("error_msg", e.getMessage());
			return "error";
		} finally {
			try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return "success";
	}
	public String runDelete() {
		session.remove("error_msg");
		Connection cn = null;
		try {
			cn = this.getCn();
			PreparedStatement pstmt = 
		    	cn.prepareStatement("DELETE FROM candy_product WHERE prod_id = ?");
			pstmt.setLong(1, this.prod_id);
			pstmt.execute(); 			
			session.put("msg", "Delete Succeeded");
		} catch (Exception e) {
			session.put("error_msg", e.getMessage());
			return "error";
		} finally {
			try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return "success";
	}		
	public String retrieveProductInformation() {
		session.remove("error_msg");
		this.data = new ArrayList<ProductUtils>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		Connection cn = null;
		try {
			cn = this.getCn();			
			String query = "SELECT prod_id, prod_desc, prod_cost, prod_price " +
			               "FROM candy_product ORDER BY prod_desc";			               	
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ProductUtils p = new ProductUtils();
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
		} finally {
			try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return "success";
	}
}
