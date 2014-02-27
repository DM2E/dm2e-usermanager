package de.mpiwg.dm2e.userManager.beans.component;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.icesoft.faces.component.ext.HtmlInputText;

import de.mpiwg.dm2e.userManager.db.bo.Role;
import de.mpiwg.dm2e.userManager.db.bo.User;
import de.mpiwg.dm2e.userManager.db.bo.UserRole;
import de.mpiwg.dm2e.userManager.utils.Hashing;

public class Register  extends AccountEditor {

	private static Logger logger = Logger.getLogger(Register.class);
	
	private HtmlInputText passwordComponent;
	
	public Register(){
		this.currentUser = new User();
	}
	
	public void listenerSaveUser(ActionEvent event){
		try {
			
			if(isNewUserOk()){
				this.setPassword(Hashing.MD5(getPassword()));
				this.currentUser.setPassword(getPassword());
				this.getDp().save(currentUser);
				
				UserRole link2DefaultRole = new UserRole();
				link2DefaultRole.getId().setUserLogin(currentUser.getLogin());
				link2DefaultRole.getId().setRoleName("omnom-user");
				
				this.getDp().saveUserRole(link2DefaultRole);
				
				this.addMsg("The user " + currentUser.getLogin() + " has been saved.");
				this.currentUser = new User();
				
				
				
			}
			
			this.loadRolesAndProps();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addMsg("Internal Error. The user could not be saved.");
		}
	}
	
	public void listenerClean(ActionEvent event){
		this.currentUser = new User();
	}

	public HtmlInputText getPasswordComponent() {
		return passwordComponent;
	}

	public void setPasswordComponent(HtmlInputText passwordComponent) {
		this.passwordComponent = passwordComponent;
	}
}
