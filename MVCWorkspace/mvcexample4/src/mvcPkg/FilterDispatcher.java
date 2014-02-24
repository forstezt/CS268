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

public class FilterDispatcher implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	public void destroy() {
		this.filterConfig = null;
	}
	public void doFilter(ServletRequest request, 
			             ServletResponse response,	
			             FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");		
		String action = uri.substring(lastIndex + 1);
		String dispatchURL = null;
		
		if(action.equals("Insert")) { // <-----
			DataUtilities u = new DataUtilities();  
			u.insert(req);
			dispatchURL = "InsertProduct.jsp";			
			
		} else if(action.equals("Delete")) {
			DataUtilities u = new DataUtilities();
			u.delete(req);	
			u.retrieveProductInformation(req);
			dispatchURL = "DeleteProduct.jsp";			
			
		} else if(action.equals("DeleteProduct")) {
			DataUtilities u = new DataUtilities();
			u.retrieveProductInformation(req);
			dispatchURL = "DeleteProduct.jsp";			
				
		} else if(action.equals("InsertProduct")) {
			dispatchURL = "InsertProduct.jsp";			
		}

		if(dispatchURL == null) {
			// let other static resources through
			filterChain.doFilter(request, response);
		} else {
			res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			res.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
			res.setHeader("Pragma", "no-cache"); 			
			RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);	// internal page change - browser doesn't see change in address bar					
		}
	}
}
