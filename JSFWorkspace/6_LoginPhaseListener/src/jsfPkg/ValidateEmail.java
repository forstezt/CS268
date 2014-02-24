package jsfPkg;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class ValidateEmail implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
	    String email = (String) value;
	    if(email == null || email.equals("")) {
	        ((UIInput)toValidate).setValid(false);
	        context.addMessage(toValidate.getClientId(context), new FacesMessage("Email is required"));	    	
	    } else if (!email.contains("@")) {
	        ((UIInput)toValidate).setValid(false);
	        context.addMessage(toValidate.getClientId(context), new FacesMessage("Email Address must contain @"));
	    }		
	}
}
