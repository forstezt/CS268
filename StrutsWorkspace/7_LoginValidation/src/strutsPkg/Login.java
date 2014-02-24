package strutsPkg;

import java.sql.*;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;

public class Login extends ActionSupport implements Validateable, SessionAware, Preparable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String error_msg;
	private String login_msg;
	private SessionMap<String, Object> session;
	
	public String execute() {
		return "success";
	}
	public String checkLogin() {
		if(session.get("cust_id") == null) {
			return "login";			
		}
		return "success";
	}
	public void validate() {
		// won't be null if submitting the login form
		// can be null if user tries to bypass login and enter display (or something other than login) in address bar
		if(username != null && password != null) {
			if(this.username.length() == 0) {
				// in struts.xml must have a result with name="input" for this to work
				// use dispatcher and specify Login.jsp
				addFieldError("username", "User name is required");
			} else if(this.password.length() == 0) {
				// in struts.xml must have a result with name="input" for this to work
				// use dispatcher and specify Login.jsp
				addFieldError("password", "Password is required");
			}			
		}
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
				ret = "success";
			} else {
				this.login_msg = "Invalid Login";
			    session.put("login_msg", "Invalid Login");
			}
			cn.close();	
		} catch (ClassNotFoundException e) {
			this.error_msg = e.getMessage();
			ret = "error";
		} catch (SQLException e) {
			this.error_msg = e.getMessage();
			ret = "error";
		} 
		return ret;
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
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String errorMsg) {
		error_msg = errorMsg;
	}	
	public String getLogin_msg() {
		return login_msg;
	}
	public void setLogin_msg(String loginMsg) {
		login_msg = loginMsg;
	}
	public Map<String, Object> getSession() {
		return this.session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>)session;		
	}
	@Override
	public void prepare() throws Exception {
		clearErrorsAndMessages();		
	}
}
