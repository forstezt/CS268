package jsfPkg;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

// this let writes before and after phase messages to the console as they are called
public class PhaseListenerLifeCycle implements PhaseListener {
	private static final long serialVersionUID = 1L;
	private int count;	
	
	public PhaseListenerLifeCycle() {
		super();
		System.out.println("-- PhaseListenerLifeCycle in constructor");
	}
	public void beforePhase(PhaseEvent event) {
		System.out.println("-- PhaseListenerLifeCycle beforePhase: " + event.getPhaseId());
	}
	public void afterPhase(PhaseEvent event) {
       System.out.println("-- PhaseListenerLifeCycle afterPhase: " + event.getPhaseId());	
       if(event.getPhaseId().toString().equals("RENDER_RESPONSE 6")) {
    	   System.out.println("Count " + count);
    	   count++;
    	   System.out.println("");
       }
	}
	public PhaseId getPhaseId() {
		System.out.println("PhaseListenerLifeCycle in getPhaseId");
	    return PhaseId.ANY_PHASE;
	}

}
