package strutsPkg;

import java.sql.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Login implements SessionAware {
	private String username;
	private String password;
	private SessionMap<String, Object> session = null;
	
	@SuppressWarnings("unchecked")
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;
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

	public String validateLogin() {
		String ret = "login";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
	
		    String sqlQuery = "SELECT cust_id FROM candy_customer WHERE  username=? AND password=?";			    
			PreparedStatement pStmt = cn.prepareStatement(sqlQuery);				
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			
			ResultSet rs = 	pStmt.executeQuery();
			if (rs.next()) {
				session.put("cust_id", rs.getLong("cust_id"));
				session.remove("login_msg");
				ret = "success";
			} else {
			    session.remove("cust_id");
			    session.put("login_msg", "Invalid Login");
			}
			cn.close();	
		} catch (ClassNotFoundException e) {
			ret = "error";
			session.put("error_msg", e.getMessage());
		} catch (SQLException e) {
			session.put("error_msg", e.getMessage());
			ret = "error";
		} 
		return ret;
	}	
}
