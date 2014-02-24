package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

public class ActionInsert implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelProduct product = new ModelProduct();  
		product.runQuery(request, "insert");
		request.setAttribute("product", product);
		RequestDispatcher rd = request.getRequestDispatcher("AddProduct.jsp");
		rd.forward(request, response);				 
   }
}
