package jsfPkg;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class PhaseListenerCaching implements PhaseListener {
	private static final long serialVersionUID = 1L;
	public PhaseListenerCaching() {
		super();
		//System.out.println("-- PhaseListenerCaching in constructor");
	}
	public PhaseId getPhaseId() {
		// tells listener which lifecycle phase to use
		// before RENDER_RESPONSE phase next step is to 
		// Add the http headers preventing caching.
		//System.out.println("PhaseListenerCaching in getPhaseId");
		return PhaseId.RENDER_RESPONSE;
	}
	public void afterPhase(PhaseEvent arg0) {
		//System.out.println("-- PhaseListenerCaching in afterPhase: " + arg0.getPhaseId());
	}
	public void beforePhase(PhaseEvent arg0) {		
		// don't allow users to directly access this page without first logging in
		//System.out.println("-- PhaseListenerCaching in beforePhase: " + arg0.getPhaseId());
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
		response.setHeader("Pragma", "no-cache"); // Deprecated, but still used by old browsers (IE6, I think)		
	}
}
