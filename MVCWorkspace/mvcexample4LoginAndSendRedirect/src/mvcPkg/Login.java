package mvcPkg;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;

public class Login {
	public boolean processLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String msg = "";
		boolean ret = false;
		MySQL ms = new MySQL();
		ms.open();
		try {
		    String sqlQuery = "SELECT cust_id FROM candy_customer " +
		    	              "WHERE  username=? AND password=?";
		    
			PreparedStatement pStmt = ms.cn.prepareStatement(sqlQuery);
			
			pStmt.setString(1, username);
			pStmt.setInt(2, Integer.parseInt(password));
			
			ResultSet rs = 	pStmt.executeQuery();
			if (rs.next()) {
			    // use request.getSession().setAttribute - not request.setAttribute
			    request.getSession().setAttribute("cust_id", rs.getString("cust_id"));
			    ret = true;
			} else {
			    msg = "Invalid Login";
			}
		} catch (Exception e) {
			msg = "Error: " + e.getMessage();			
		} 
		ms.close();		
		request.setAttribute("msg", msg);
		return ret;
	}
}
