package edu.uwec.cs.morriscm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

public class FilterDispatcher implements Filter {
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	public void destroy() {}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.commands.put("home", new ActionHome());
		this.commands.put("addproduct", new ActionAddProduct());		
		this.commands.put("insert", new ActionInsert());
		this.commands.put("delete", new ActionDelete());
		this.commands.put("deleteproduct", new ActionDeleteProduct());
		this.commands.put("selectproducttoupdate", new ActionSelectProductToUpdate());		
		this.commands.put("updateproduct", new ActionUpdateProduct());		
		this.commands.put("update", new ActionUpdate());
		
		// login.jsp is the only action using a file extension
		// this is useful in doFilter's logic determining what to do 
		// with a login.jsp action or direct call to login.jsp
		this.commands.put("login.jsp", new ActionLogin()); 
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String uri = req.getRequestURI().toLowerCase();
		int lastIndex = uri.lastIndexOf("/");		
		String action = uri.substring(lastIndex + 1).toLowerCase();
		
		boolean actionHasFileExtention = action.contains(".");
		
		if(actionHasFileExtention && action.contains(".jsp") == false) {
			// let images, css and js pages pass through 
			// (actions - all but login.jsp have no file extension - go to the else)
			filterChain.doFilter(request, response);			
		} else {
			// if an action is requested and it isn't the login.jsp action, I want to check for a valid login
			if(action.equals("login.jsp") == false) {
				if(ModelLoginUtilities.checkLogin(req) == false) {
					Command command = commands.get("login.jsp");
					command.execute(req, res);
					return;
				} 
			} 				
			
			// if any jsp page other than login.jsp is directly requested it will be ignored since it won't be 
			// found in the commands collection - so a user can't directly call a jsp page unless it is login.jsp
			// navbar uses action names (with no file extension) in its href's for all but login.jsp
			Command command = commands.get(action);
			if(command == null) {
				command = commands.get("home");		
			}			
			command.execute(req, res);
		}
	}
}
