package jsfPkg;

import java.io.Serializable;
import java.sql.*;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class LoginUtilities implements Serializable {
	private static final long serialVersionUID = 1L;
	private String f_userid;
	private String f_pin;
	private String msg;
	
	public String validateLogin() {
		String ret = "login";
		this.msg = "";
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
				ret = "displaycourses";
				// don't close connection - use it in displaycourses (next)
			} else {
				this.msg = "Invalid Login";
				MySQL.close();			
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();
			ret = "error";
			MySQL.close();			
		}
		return ret; 
	}	

	public String getF_userid() {
		return f_userid;
	}
	public void setF_userid(String fUserid) {
		f_userid = fUserid;
	}
	public String getF_pin() {
		return f_pin;
	}
	public void setF_pin(String fPin) {
		f_pin = fPin;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
