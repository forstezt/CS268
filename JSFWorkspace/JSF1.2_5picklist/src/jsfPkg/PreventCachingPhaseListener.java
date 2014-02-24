package jsfPkg;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class PreventCachingPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;
	public PhaseId getPhaseId() {
		// tells listener which lifecycle phase to use
		// before RENDER_RESPONSE phase next step is to 
		// Add the http headers preventing caching.
		return PhaseId.RENDER_RESPONSE;
	}
	public void afterPhase(PhaseEvent arg0) {
	}
	public void beforePhase(PhaseEvent arg0) {		
		System.out.println("PreventCachingPhaseListener beforePhase method");
		// don't allow users to directly access this page without first logging in
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); 		//HTTP 1.0 - works with older browsers
		response.setDateHeader ("Expires", 0);          //prevents caching at the proxy server		
	}
}
