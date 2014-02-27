package de.mpiwg.dm2e.userManager.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;

import com.icesoft.faces.component.ext.HtmlInputText;

public class PasswordValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if(component.getAttributes().get("passwordComponent") != null){
			HtmlInputText passwordComponent = (HtmlInputText)component.getAttributes().get("passwordComponent");
			
			String secondPwd = (String)value;
			if(!StringUtils.equals((String)passwordComponent.getValue(), secondPwd)){
				FacesMessage msg = new FacesMessage();
				msg.setDetail("Passwords has to be equals");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);		
			}else if(secondPwd.length() < 6){
				FacesMessage msg = new FacesMessage();
				msg.setDetail("Password has to be longer");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}	
		}
		

	}
}
