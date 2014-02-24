package strutsPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class CustomerUtilities implements SessionAware {
	private long cust_id;
	private String cust_name;
	private String cust_type;
	private String cust_type_desc;
	private String cust_addr;
	private String cust_zip;
	private String cust_phone;
	private String username;
	private String password;
	private Connection cn = null;
	private String msg;
	private ArrayList<Customer> cData;
	private ArrayList<CustomerType> tData;
	private SessionMap<String, Object> session = null;

	public String insertCustomer() {
		return runQuery("insert");
	}
	public String deleteCustomer() {
		return runQuery("delete");
	}	
	public String updateCustomer() {
		return runQuery("update");
	}
	public String runQuery(String action) {
		String ret = "success";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "";
			if(action.equals("insert")) {
				sqlQuery = "INSERT INTO candy_customer(cust_name, cust_type, cust_addr, cust_zip, cust_phone)VALUES(?,?,?,?,?);";
				PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setString(1, cust_name);
				pstmt.setString(2, cust_type);
				pstmt.setString(3, cust_addr);
				pstmt.setString(4, cust_zip);
				pstmt.setString(5, cust_phone);
				// execute the query
				pstmt.execute();
			} else if(action.equals("delete")) {
				sqlQuery = "DELETE FROM candy_customer WHERE cust_id = ?";
				PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setLong(1, cust_id);
				pstmt.execute();
			} else if(action.equals("update")) {
				sqlQuery = "UPDATE candy_customer SET cust_name=?, cust_type=?, cust_addr=?, cust_zip=?, cust_phone=? WHERE cust_id=?;";
				PreparedStatement pstmt = cn.prepareStatement(sqlQuery);
				pstmt.setString(1, cust_name);
				pstmt.setString(2, cust_type);
				pstmt.setString(3, cust_addr);
				pstmt.setString(4, cust_zip);
				pstmt.setString(5, cust_phone);
				pstmt.setLong(6, cust_id);
				
				pstmt.execute();
			}
		} catch (Exception e) {
			this.msg = action + " failed<br />\n" + e.getMessage();	
			ret = "error";
		} finally {
			if(action.equals("insert")) {
				try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
			} else {
				// save the connection to use in retrieveCandyCustTypes()
				session.put("cn", cn);
			}
		}
		return ret;
	}
	public String retrieveCustomers() {
		String ret = "success";
		this.cData = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// here is where we reuse the existing connection if an update or delete was
			// executed just prior to calling this method (if not re-make the connection)
			getCn();
			if(cn == null || cn.isClosed()) {
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			}			
			//create the query statement
			String sqlQuery = "SELECT cust_id, cust_name, candy_customer.cust_type, cust_type_desc, cust_addr, cust_zip, cust_phone, username, password " +
			                  "FROM candy_customer INNER JOIN candy_cust_type ON candy_customer.cust_type = candy_cust_type.cust_type " +
			                  "ORDER BY cust_name";
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				Customer c = new Customer();
				c.setCust_id(rs.getLong("cust_id"));
				c.setCust_name(rs.getString("cust_name"));
				c.setCust_type(rs.getString("cust_type"));
				c.setCust_type_desc(rs.getString("cust_type_desc"));
				c.setCust_addr(rs.getString("cust_addr"));
				c.setCust_zip(rs.getString("cust_zip"));
				c.setCust_phone(rs.getString("cust_phone"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
				this.cData.add(c);
			}
		} catch (ClassNotFoundException e) {
			this.msg = e.getMessage();
			ret = "error";
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} finally {
		    try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }			
		}
		return ret;
	}
	public String retrieveCandyCustTypes() {
		String ret = "success";
		this.tData = new ArrayList<CustomerType>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// here is where we reuse an existing connection if retrieveSingleCourse
			// executed just prior to calling this method (if not remake the connection)
			getCn();
			if(cn == null || cn.isClosed()) {
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			}			
			//create the query statement			
			String sqlQuery = "SELECT cust_type, cust_type_desc FROM candy_cust_type ORDER BY cust_type_desc";	
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				CustomerType cct = new CustomerType();
				cct.setCust_type(rs.getString("cust_type"));
				cct.setCust_type_desc(rs.getString("cust_type_desc"));
				this.tData.add(cct);
			}
		} catch (ClassNotFoundException e) {
			this.msg = e.getMessage();
			ret = "error";
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} finally {
		    try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }			
		}	
		return ret;
	}
	public String retrieveSingleCustomer() {
		String ret = "success";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			//create the query statement
			String sqlQuery = "SELECT cust_id, cust_name, candy_customer.cust_type, cust_type_desc, cust_addr, cust_zip, cust_phone, username, password " +
                              "FROM candy_customer INNER JOIN candy_cust_type ON candy_customer.cust_type = candy_cust_type.cust_type " +
                              "WHERE cust_id = " + this.cust_id; 
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				cust_id = rs.getLong("cust_id");
				cust_name = rs.getString("cust_name");
				cust_type = rs.getString("cust_type");
				cust_type_desc = rs.getString("cust_type_desc");
				cust_addr = rs.getString("cust_addr");
				cust_zip = rs.getString("cust_zip");
				cust_phone = rs.getString("cust_phone");
				username = rs.getString("username");
				password = rs.getString("password");
			}
		} catch (ClassNotFoundException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} 
		if(ret.equals("success")) {
			return retrieveCandyCustTypes();
		} else {
			return ret;
		}
	}
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long custId) {
		cust_id = custId;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String custName) {
		cust_name = custName;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String custType) {
		cust_type = custType;
	}
	public String getCust_type_desc() {
		return cust_type_desc;
	}
	public void setCust_type_desc(String cust_type_desc) {
		this.cust_type_desc = cust_type_desc;
	}	
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String custAddr) {
		cust_addr = custAddr;
	}
	public String getCust_zip() {
		return cust_zip;
	}
	public void setCust_zip(String custZip) {
		cust_zip = custZip;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String custPhone) {
		cust_phone = custPhone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Customer> getCData() {
		return cData;
	}
	public void setCData(ArrayList<Customer> cData) {
		this.cData = cData;
	}
	public ArrayList<CustomerType> getTData() {
		return tData;
	}
	public void setTData(ArrayList<CustomerType> tData) {
		this.tData = tData;
	}
	public SessionMap<String, Object> getSession() {
		return session;
	}
	public void setSession(SessionMap<String, Object> session) {
		this.session = session;
	}
	public void getCn() {
		if(this.cn == null) {
			this.cn = (Connection)session.get("cn");
		}
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;		
	}	
}
