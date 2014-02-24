package edu.uwec.cs.morriscm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelLoginUtilities {
	private String msg;
	public static boolean checkLogin(HttpServletRequest request) {
	    boolean found = false;
	    // use request.getSession().getAttribute - not request.getAttribute
		if(request.getSession().getAttribute("cust_id") != null) {
			found = true;
		} 
		return found;
	}
	
	public boolean processLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean ret = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
	
		    String sqlQuery = "SELECT cust_id FROM candy_customer " +
		    	              "WHERE  username=? AND password=?";
		    
			PreparedStatement pStmt = cn.prepareStatement(sqlQuery);
			
			pStmt.setString(1, username);
			pStmt.setInt(2, Integer.parseInt(password));
			
			ResultSet rs = 	pStmt.executeQuery();
			if (rs.next()) {
			    // use request.getSession().setAttribute - not request.setAttribute
			    request.getSession().setAttribute("cust_id", rs.getString("cust_id"));
			    ret = true;
			} else {
			    this.msg = "Invalid Login";
			}
			//Clean up resources
			cn.close();		
		} catch (Exception e) {
			this.msg = "Error: " + e.getMessage();			
		} 
		request.setAttribute("msg", this.msg);
		return ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
