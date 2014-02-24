package edu.uwec.cs.morriscm;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
		/*
			The init method is executed only when the filter is first initialized. It is not
			executed each time the filter is invoked. For simple filters you can provide an
			empty body to this method, but there are two common reasons for using init.
			First, the FilterConfig object provides access to the servlet context and to
			the name of the filter that is assigned in the web.xml file. So, it is common to
			use init to store the FilterConfig object in a field so that the doFilter
			method can access the servlet context or the filter name. This process is
			described in Section 9.3. Second, the FilterConfig object has a getInit-
			Parameter method that lets you access filter initialization parameters that are
			assigned in the deployment descriptor (web.xml).
		 */
	}
	public void doFilter(ServletRequest request, 
			             ServletResponse response,	
			             FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		
		// this attempt to block access to css and js pages no longer works due to aggressive browser caching
		if(uri.indexOf("/CSS/") != -1 && req.getHeader("referer") == null){
			// don't allow visitors to see the css files
			res.sendError(HttpServletResponse.SC_FORBIDDEN);	
		} else if(uri.indexOf("/JSLibrary/") != -1 && req.getHeader("referer") == null){
			// don't allow visitors to see javascript files
			res.sendError(HttpServletResponse.SC_FORBIDDEN);								
		} else { 
			// let other static resources through
			filterChain.doFilter(request, response);
		}
	}
}
