package jsfPkg;

import java.sql.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginUtils {
	private String username;
	private String password;
	private String login_msg;	

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
	public String getLogin_msg() {
		return login_msg;
	}
	public void setLogin_msg(String login_msg) {
		this.login_msg = login_msg;
	}
	public String validateLogin() {
		String ret = "login";
		this.login_msg = "success";
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		try {
			MySQL.open();	
		    String sqlQuery = "SELECT cust_id FROM candy_customer WHERE  username=? AND password=?";			    
			PreparedStatement pStmt = MySQL.cn.prepareStatement(sqlQuery);				
			pStmt.setString(1, this.username);
			pStmt.setString(2, this.password);
			
			ResultSet rs = 	pStmt.executeQuery();
			if (rs.next()) {
				session.setAttribute("cust_id", rs.getString("cust_id"));
				ret = "DisplayProduct";
				// don't close connection - going to displayproduct next
			} else {
				session.removeAttribute("cust_id");
				this.login_msg = "Invalid Login";
				MySQL.close();
			}
		} catch (SQLException e) {
			this.login_msg = e.getMessage();
			ret = "error";
			MySQL.close();
		} 
		return ret; 
	}
}
