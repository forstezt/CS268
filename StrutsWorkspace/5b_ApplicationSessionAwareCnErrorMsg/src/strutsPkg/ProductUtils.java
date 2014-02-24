package strutsPkg;
import java.io.Serializable;
import java.sql.*;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import java.text.*;
import java.util.*;

// This isn't a good idea.  Read this about allowing different threads to use the same connection:
// http://dev.mysql.com/news-and-events/newsletter/2003-04/a0000000154.html 
public class ProductUtils implements Serializable, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;
	Product p = new Product();
	private Connection cn;
	private ArrayList<Product> data;
	private SessionMap<String, Object> session = null;
	private Map<String, Object> application = null;
	
	public void setApplication(Map<String, Object> arg0) {
		application = (Map<String, Object>)arg0;
	}	
	public void setSession(Map<String, Object> arg0) {
		session = (SessionMap<String, Object>)arg0;
	}
	private Connection getCn() {
		return (Connection)application.get("cn");
	}	
	public ArrayList<Product> getData() {
		return data;
	}	
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public String runInsert() {
		session.remove("error_msg");
		try {
			cn = getCn();
			if(cn == null || cn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
						                       "STUDENT", "$5333");
				// save some time and reuse the existing connection for everyone
				application.put("cn", cn);
			}
			
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, p.getProd_desc());
			pstmt.setFloat(2, p.getProd_cost());
			pstmt.setFloat(3, p.getProd_price());
			pstmt.execute(); 			
			session.put("msg", "Success");
		} catch (Exception e) {
			session.put("error_msg", e.getMessage());
			return "error";
		}
		return "success";
	}
	public String runDelete() {
		session.remove("error_msg");
		try {
			cn = getCn();
			if(cn == null || cn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
						                       "STUDENT", "$5333");
				// save some time and reuse the existing connection for everyone
				application.put("cn", cn);
			}			

			PreparedStatement pstmt = 
		    	cn.prepareStatement("DELETE FROM candy_product WHERE prod_id = ?");
			pstmt.setLong(1, p.getProd_id());
			pstmt.execute(); 			
			session.put("msg", "Delete Succeeded");
		} catch (Exception e) {
			session.put("error_msg", e.getMessage());
			return "error";
		}
		return "success";
	}		
	public String retrieveProductInformation() {
		session.remove("error_msg");
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		Connection cn = null;
		try {
			cn = getCn();
			if(cn == null || cn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
						                       "STUDENT", "$5333");
				// save some time and reuse the existing connection for everyone
				application.put("cn", cn);
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
			session.put("error_msg", e.getMessage());
			return "error";
		} 
		return "success";
	}
}
