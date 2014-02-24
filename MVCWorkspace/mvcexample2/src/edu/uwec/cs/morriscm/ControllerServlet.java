package edu.uwec.cs.morriscm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class ControllerServlet extends HttpServlet {
   static final long serialVersionUID = 1L;
   
	public ControllerServlet() {
		super();
	}   	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		String dispatchURL = null;
		
		// execute an action and set next page to view
		if(action.equals("insert.action")) { // <-----
			DataUtilities u = new DataUtilities();  
			u.insert(request);
			// store product to allow view to access product properties
			dispatchURL = "AddProduct.jsp";
			
		} else if(action.equals("delete.action")) {
			DataUtilities u = new DataUtilities();
			u.delete(request);	
			u.retrieveProductInformation(request);
			// store product to allow view to access product properties
						
			dispatchURL = "DeleteProduct.jsp";
			
		} else if(action.equals("DeleteProduct.action")) {
			DataUtilities u = new DataUtilities();
			u.retrieveProductInformation(request);
			// store product to allow view to access product properties
			dispatchURL = "DeleteProduct.jsp";
			
		} else if(action.equals("AddProduct.action")) {
			dispatchURL = "AddProduct.jsp";
		}
		
		if(dispatchURL != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);	// internal page change - browser doesn't see change in address bar
		}
	}
}