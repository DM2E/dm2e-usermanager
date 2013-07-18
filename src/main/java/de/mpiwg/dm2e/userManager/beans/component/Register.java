package de.mpiwg.dm2e.userManager.beans.component;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.db.bo.User;

public class Register  extends AccountEditor {

	private static Logger logger = Logger.getLogger(Register.class);
	
	public Register(){
		this.currentUser = new User();
	}
	
	public void listenerSaveUser(ActionEvent event){
		try {
			
			if(isNewUserOk()){
				this.currentUser.setPassword(getPassword());
				this.getDp().save(currentUser);
				this.addMsg("The user " + currentUser.getLogin() + " has been saved.");
				this.currentUser = new User();
			}
			
			this.loadRolesAndProps();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addMsg(e.getMessage());
		}
	}
	
	public void listenerClean(ActionEvent event){
		this.currentUser = new User();
	}

}
