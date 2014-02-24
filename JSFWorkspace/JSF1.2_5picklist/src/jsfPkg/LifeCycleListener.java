package jsfPkg;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

// this let you see phases as they are called in the console
public class LifeCycleListener implements PhaseListener {
	private static final long serialVersionUID = 1L;
	private int count;
	
	public void afterPhase(PhaseEvent event) {
	       System.out.println("AfterPhase: " + event.getPhaseId());	
	       if(event.getPhaseId().toString().equals("RENDER_RESPONSE 6")) {
	    	   System.out.println("Count " + count);
	    	   count++;
	    	   System.out.println("");
	       }
	}

	public void beforePhase(PhaseEvent event) {
		System.out.println("BeforePhase: " + event.getPhaseId());
	}

	public PhaseId getPhaseId() {
	       return PhaseId.ANY_PHASE;
	}

}
