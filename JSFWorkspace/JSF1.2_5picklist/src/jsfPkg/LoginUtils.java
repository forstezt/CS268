package jsfPkg;

import java.sql.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginUtils {
	private String f_userid;
	private String f_pin;
	private String login_msg;
	
	public String validateLogin() {
		System.out.println("LoginUtils validateLogin");
		String ret = "login";
		this.login_msg = "success";
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		try {
			MySQL.open();	
		    String sqlQuery = "SELECT f_id FROM nw_faculty WHERE  f_userid=? AND f_pin=?";			    
			PreparedStatement pStmt = MySQL.cn.prepareStatement(sqlQuery);				
			pStmt.setString(1, this.f_userid);
			pStmt.setString(2, this.f_pin);
			
			ResultSet rs = 	pStmt.executeQuery();
			if (rs.next()) {
				session.setAttribute("f_id", rs.getString("f_id"));
				ret = "DisplayCourse";
				// don't close connection - use it in DisplayCourse (next)
			} else {
				session.removeAttribute("f_id");
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
	public String getF_userid() {
		return f_userid;
	}
	public void setF_userid(String f_userid) {
		this.f_userid = f_userid;
	}
	public String getF_pin() {
		return f_pin;
	}
	public void setF_pin(String f_pin) {
		this.f_pin = f_pin;
	}
	public String getLogin_msg() {
		return login_msg;
	}
	public void setLogin_msg(String login_msg) {
		this.login_msg = login_msg;
	}	
}
