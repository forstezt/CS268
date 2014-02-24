package strutsPkg;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerFilter implements Filter {
	public void init(FilterConfig arg0) throws ServletException {		
	}
	public void destroy() {		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		
		int lastIndex = uri.lastIndexOf("/");		
		String action = uri.substring(lastIndex + 1).toLowerCase();
		
		boolean actionHasFileExtention = action.contains(".");
		
		if(actionHasFileExtention && action.contains(".jsp") == false) {
			// let images, css and js pages pass through 
			// actions and requests for jsp pages go to the else
			filterChain.doFilter(request, response);
		} else {
			String dispatchURL = "";
			if(action.equals("display")) {
				Product product = new Product();  
				//product.retrieveProductInformation(req, null);
				req.setAttribute("product", product);
				dispatchURL = "DisplayProduct.jsp";
		    } else if(action.equals("insert")) {
		    	Product product = new Product(); 
				//product.runInsert(req);
				req.setAttribute("product", product);
				dispatchURL = "DisplayProduct.jsp";
			} else if(action.equals("input")) {
				dispatchURL = "InputInsert.jsp";			
			} else {
				dispatchURL = uri.substring(lastIndex + 1);
			}
			// prevent caching
			res.setHeader("Cache-Control","no-cache"); //HTTP 1.1
			res.setHeader("Pragma","no-cache"); 	   //HTTP 1.0 - works with older browsers
			res.setDateHeader("Expires", 0);           //prevents caching at the proxy server
			
			RequestDispatcher rd = req.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);	// internal page change - browser doesn't see change in address bar
		}
	}
}
