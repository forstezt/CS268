<%  
 	Cookie cookies[] = request.getCookies();
    boolean found = false;
	if(cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if ("f_id".equals(cookie.getName())) {
				found = true;
				break;
			}
		}
	} 
	if(!found) response.sendRedirect("login.jsp"); 
%>