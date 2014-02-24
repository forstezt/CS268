package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import edu.uwec.cs.morriscm.Command;

import java.io.IOException;

public class ActionAddProduct implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("AddProduct.jsp");
		rd.forward(request, response);	// internal page change - browser doesn't see change in address bar					 
   }
}
