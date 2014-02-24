package jsfPkg;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class PhaselistenerCaching implements PhaseListener {
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
		// don't allow users to directly access this page without first logging in
		// this syntax works with current browsers as of 4/29/2011
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
		response.setHeader("Pragma", "no-cache");	
	}
}
