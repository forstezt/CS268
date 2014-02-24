package edu.uwec.cs.morriscm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;


/*
 * This execute method is called when the login.jsp page is initially displayed (no user inputs yet)
 * and later when the login.jsp page is submitted - meaning is "will" have parameters named username and password
 * The presence or absence of the username and password parameters is used to determine if login.processLogin is called
 */
public class ActionLogin implements Command {
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dispatchURL = "Login.jsp";
		request.getSession().removeAttribute("cust_id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username != null && password != null) {
			ModelLoginUtilities login = new ModelLoginUtilities();
			if(login.processLogin(request, response)) {
				ModelProduct p = new ModelProduct(); 
				dispatchURL = "Home.jsp";			
				p.retrieveProductInformation(request);
				request.setAttribute("product", p);
			}
		}
	    RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
		rd.forward(request, response);					 
   }
}
