package mvcPkg;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;  //
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest; //
import javax.servlet.http.HttpServletResponse; //
import javax.servlet.http.HttpSession;

public class FilterDispatcher implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	public void destroy() {
		this.filterConfig = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");		
		String action = uri.substring(lastIndex + 1).toLowerCase();		
		String fileExtension = action.substring(action.lastIndexOf(".") + 1);
		String dispatchURL = null;
		
		if(action.equals("processlogin")) { 
			Login login = new Login();
			if(login.processLogin(req)) {
				dispatchURL = "Home.jsp";
			} else {
				dispatchURL = "Login.jsp";
			}
		} else if(action.equals("login")) {
		    req.getSession().removeAttribute("cust_id");
			dispatchURL = "Login.jsp";			
			
		} else if(req.getSession().getAttribute("cust_id") == null && !(fileExtension.equals("jpg") || 
				                                                        fileExtension.equals("css") ||
				                                                        fileExtension.equals("js") ||
				                                                        fileExtension.equals("htm"))) {
			// if they request "any" file other than the allowed extensions and cust_id isn't set, send them to Login.jsp
			dispatchURL = "Login.jsp";
			
		} else if(action.equals("addproduct")){
			dispatchURL = "AddProduct.jsp";	
			
		} else if(action.equals("_addproduct.jsp")) {
			dispatchURL = "AddProduct.jsp";
		
		} else if(action.equals("insert")) { 
			dispatchURL = "_AddProduct.jsp";
			Utilities u = new Utilities();  
			u.insert(req);
			
		} else if(action.equals("delete")) {
			dispatchURL = "DeleteProduct.jsp";
			Utilities u = new Utilities();
			u.delete(req);	
			u.retrieveProductInformation(req);
			
		} else if(action.equals("deleteproduct")) {
			dispatchURL = "DeleteProduct.jsp";
			Utilities u = new Utilities();
			u.retrieveProductInformation(req);
				
		} 
		if(dispatchURL == null) {
			// let other static resources through
			filterChain.doFilter(request, response);
		} else {
			// prevent caching pages used by this application (doesn't prevent caching images, css and js files)
			HttpServletResponse res = (HttpServletResponse)response;
			res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			res.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
			res.setHeader("Pragma", "no-cache");
			
			// internal page change - browser doesn't see change in address bar
//			RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
//			rd.forward(request, response);	
			
			// this will allow the browser to see the new URL in its address bar - but it results in infinite loop :-(
//			res.sendRedirect(dispatchURL);
			
			// notice I added an underscore to the dispatchURL for AddProduct.jsp after doing an insert
			// I'll look for that, and if found do a sendRedirect.  I'll add an else if looking for the underscore
			// in the dispatch URL and if found remove it and do a forward
			if(dispatchURL.substring(0, 1).equals("_")) {
				res.sendRedirect(dispatchURL);
			} else {
				request.getRequestDispatcher(dispatchURL).forward(request, response);
			}
			
			
			
		}		
	}
}
