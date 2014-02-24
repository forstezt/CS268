package strutsPkg;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerUtilities implements Serializable {
	private static final long serialVersionUID = 1L;
	private String msg;
	private Customer c = new Customer();
	private ArrayList<Customer> customers;
	private ArrayList<CustomerType> custtypes;

	public String insertCustomer() {
		MySQL ms = new MySQL();
		ms.open();
		String sqlQuery = "INSERT INTO candy_customer(cust_name, cust_type, cust_addr, cust_zip, cust_phone)VALUES(?,?,?,?,?);";
		try {
			PreparedStatement pstmt = ms.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, c.getCust_name());
			pstmt.setString(2, c.getCust_type());
			pstmt.setString(3, c.getCust_addr());
			pstmt.setString(4, c.getCust_zip());
			pstmt.setString(5, c.getCust_phone());
			// execute the query
			pstmt.execute();
			msg = "Added " + c.getCust_name();
		} catch (Exception e) {
			msg = "Adding failed: " + e.getMessage();
			return "error";
		} finally {
			ms.close();			
		}
		return "success";
	}
	public String deleteCustomer() {
		MySQL ms = new MySQL();
		ms.open();
		String sqlQuery = "DELETE FROM candy_customer WHERE cust_id = ?";
		try {
			PreparedStatement pstmt = ms.cn.prepareStatement(sqlQuery);
			pstmt.setLong(1, c.getCust_id());
			pstmt.execute();
		} catch (Exception e) {
			msg = "delete failed: " + e.getMessage();
			return "error";
		} finally {
			ms.close();
		}
		return "success";
	}	
	public String updateCustomer() {
		MySQL ms = new MySQL();
		ms.open();
		String sqlQuery = "UPDATE candy_customer SET cust_name=?, cust_type=?, cust_addr=?, cust_zip=?, cust_phone=? WHERE cust_id=?;";
		try {
			PreparedStatement pstmt = ms.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, c.getCust_name());
			pstmt.setString(2, c.getCust_type());
			pstmt.setString(3, c.getCust_addr());
			pstmt.setString(4, c.getCust_zip());
			pstmt.setString(5, c.getCust_phone());
			pstmt.setLong  (6, c.getCust_id());
			
			pstmt.execute();
			msg = "Updated " + c.getCust_name();
		} catch (Exception e) {
			msg = "update failed: " + e.getMessage();
			return "error";
		} finally {
			ms.close();
		}
		return "success";
	}

	public String retrieveCustomers() {
		String ret = "success";
		this.customers = new ArrayList<Customer>();
		MySQL ms = new MySQL();
		ms.open();
		try {
			//create the query statement
			String sqlQuery = "SELECT cust_id, cust_name, candy_customer.cust_type, cust_type_desc, cust_addr, cust_zip, cust_phone, username, password " +
			                  "FROM candy_customer INNER JOIN candy_cust_type ON candy_customer.cust_type = candy_cust_type.cust_type " +
			                  "ORDER BY cust_name";
			Statement stmt = ms.cn.createStatement();
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
				this.customers.add(c);
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} finally {
		    ms.close();		
		}
		return ret;
	}
	public String retrieveCandyCustTypes() {
		String ret = "success";
		this.custtypes = new ArrayList<CustomerType>();
		MySQL ms = new MySQL();
		ms.open();
		try {
			// here is where we reuse an existing connection if retrieveSingleCourse
			// executed just prior to calling this method (if not remake the connection)
			//create the query statement			
			String sqlQuery = "SELECT cust_type, cust_type_desc FROM candy_cust_type ORDER BY cust_type_desc";	
			Statement stmt = ms.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				CustomerType cct = new CustomerType();
				cct.setCust_type(rs.getString("cust_type"));
				cct.setCust_type_desc(rs.getString("cust_type_desc"));
				this.custtypes.add(cct);
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} finally {
		    ms.close();		
		}	
		return ret;
	}
	public String retrieveSingleCustomer() {
		String ret = "success";
		MySQL ms = new MySQL();
		ms.open();
		try {
			//create the query statement
			String sqlQuery = "SELECT cust_id, cust_name, candy_customer.cust_type, cust_type_desc, cust_addr, cust_zip, cust_phone, username, password " +
                              "FROM candy_customer INNER JOIN candy_cust_type ON candy_customer.cust_type = candy_cust_type.cust_type " +
                              "WHERE cust_id = " + c.getCust_id(); 
			Statement stmt = ms.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				c.setCust_id(rs.getLong("cust_id"));
				c.setCust_name(rs.getString("cust_name"));
				c.setCust_type(rs.getString("cust_type"));
				c.setCust_type_desc(rs.getString("cust_type_desc"));
				c.setCust_addr(rs.getString("cust_addr"));
				c.setCust_zip(rs.getString("cust_zip"));
				c.setCust_phone(rs.getString("cust_phone"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
			ret = "error";
		} finally {
			ms.close();
		}
		if(ret.equals("success")) {
			return retrieveCandyCustTypes();
		} else {
			return ret;
		}
	}
	
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public ArrayList<CustomerType> getCusttypes() {
		return custtypes;
	}
	public void setCusttypes(ArrayList<CustomerType> custtypes) {
		this.custtypes = custtypes;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
