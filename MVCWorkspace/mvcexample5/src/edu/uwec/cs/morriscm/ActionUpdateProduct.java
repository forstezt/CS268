package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

public class ActionUpdateProduct implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelProduct product = new ModelProduct();
		product.retrieveSingleProduct(request);
		request.setAttribute("product", product);
		RequestDispatcher rd = request.getRequestDispatcher("UpdateProduct.jsp");
		rd.forward(request, response);	// internal page change - browser doesn't see change in address bar					 
   }
}
