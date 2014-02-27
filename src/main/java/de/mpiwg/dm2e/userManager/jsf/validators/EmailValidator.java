package de.mpiwg.dm2e.userManager.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		try {
			InternetAddress emailAddr = new InternetAddress((String) value);
			emailAddr.validate();
		} catch (AddressException ex) {
			//ex.printStackTrace();
			FacesMessage msg = new FacesMessage();
			msg.setDetail("Email not in valid format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
