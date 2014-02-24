package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

public class ActionUpdate implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelProduct product = new ModelProduct();  
		product.runQuery(request, "update");
		request.setAttribute("product", product);
		RequestDispatcher rd = request.getRequestDispatcher("UpdateProduct.jsp");
		rd.forward(request, response);				 
   }
}
