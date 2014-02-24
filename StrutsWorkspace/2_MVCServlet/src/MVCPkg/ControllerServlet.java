package MVCPkg;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class ControllerServlet extends javax.servlet.http.HttpServlet 
                                implements javax.servlet.Servlet {
    static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		String dispatchURL = null;
		
		if(action.equals("display.action")) {
			Product product = new Product();  
			product.retrieveProductInformation(request, null);
			dispatchURL = "DisplayProduct.jsp";
	    } else if(action.equals("insert.action")) {
	    	Product product = new Product(); 
			product.runInsert(request);
			dispatchURL = "DisplayProduct.jsp";
		} else if(action.equals("input.action")) {
			dispatchURL = "InputInsert.jsp";			
		}		
		if(dispatchURL != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);
		}
	}
}
 