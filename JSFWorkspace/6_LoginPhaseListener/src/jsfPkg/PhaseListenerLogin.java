package jsfPkg;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

public class PhaseListenerLogin implements PhaseListener {
	private static final long serialVersionUID = 1L;

	public PhaseId getPhaseId() {
		// tells listener which lifecycle phase to use
		// after RESTORE_VIEW phase backing beans have been updated
		// from form data submitted from the Web page
		return PhaseId.RESTORE_VIEW;
	}

	public void beforePhase(PhaseEvent e) {
	}

	public void afterPhase(PhaseEvent event) {
		System.out.println("LoginPhaseListener afterPhase method");
		FacesContext fc = event.getFacesContext();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		// Check to see if they are on the login page.
		String view = fc.getViewRoot().getViewId();
		if(view.toLowerCase().lastIndexOf("login") > -1) {
			// they are on the login page.  Remove the f_id session variable.  
			// If they are unsuccessful logging in,
			// don't allow them back to a page requiring a login.
			session.removeAttribute("f_id");
		} else {
			// they aren't requesting the login page, so see if they are logged in
			if(session.getAttribute("f_id") == null) {
				// they aren't logged in, send them to the login page
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "login");
			}	
		}
	}
}
