package strutsPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.StrutsStatics;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	
	// this method is called by default when it is in a struts interceptor class
	public String intercept(ActionInvocation invocation) throws Exception {
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);
	    HttpServletResponse response = (HttpServletResponse) context.get(StrutsStatics.HTTP_RESPONSE);
	    HttpSession session =  request.getSession (true);
		
	    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
		response.setHeader("Pragma", "no-cache"); // Deprecated, but still used by old browsers (IE6, I think)
	    
        String username = request.getParameter("username");
	    Object cust_id = session.getAttribute ("cust_id");

	    if(username == null && cust_id == null) {
	    	// user isn't logged in
	        return "login";  	    	
	    } else {
	    	// user is either logged in or requesting login.jsp - send them on their way	
			return invocation.invoke();
	    }
 	}
}