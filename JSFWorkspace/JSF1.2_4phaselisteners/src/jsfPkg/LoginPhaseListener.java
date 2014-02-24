package jsfPkg;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

public class LoginPhaseListener implements PhaseListener {
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
		System.out.println("LogionPhaseListener afterPhase method");
		FacesContext fc = event.getFacesContext();

		// Check to see if they are on the login page.
		boolean loginPage;
		String view = fc.getViewRoot().getViewId();
		if(view.toLowerCase().lastIndexOf("login") > -1) {
			loginPage = true;
		} else {
			loginPage = false;
		}
		
		if (!loginPage) {
			// see if they are logged in
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			if(session.getAttribute("cust_id") == null) {
				// they aren't logged in, send them to the login page
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "login");
			}			
		}
	}

}
