package morriscm.versions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

@WebServlet("/Versions")
public class Versions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Versions() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Versions</title>");
		out.println("</head>");
		out.println("<body>");
		out.print("Servlet Engine: ");
		out.println(request.getSession().getServletContext().getMajorVersion() + "." + request.getSession().getServletContext().getMinorVersion() + "<br />");
		
		out.print("JSP Engine: ");
		out.println(JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() + "<br />");
		
		out.print("Application Server: ");
		out.println(getServletConfig().getServletContext().getServerInfo());
		out.println("</body>");
		out.println("</html>");
	}

}
