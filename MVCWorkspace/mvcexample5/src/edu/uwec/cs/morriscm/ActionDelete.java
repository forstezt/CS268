package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

public class ActionDelete implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelProduct product = new ModelProduct();
		product.runQuery(request, "delete");	
		product.retrieveProductInformation(request);
		request.setAttribute("product", product);
		RequestDispatcher rd = request.getRequestDispatcher("DeleteProduct.jsp");
		rd.forward(request, response);				 
   }
}
