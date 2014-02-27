package de.mpiwg.dm2e.userManager.beans.component;

import java.security.Principal;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

public class OwnAccount extends AccountEditor {

	private static Logger logger = Logger.getLogger(AccountsManager.class);
	
	public boolean isLoadAccount(){
		Principal p = getSessionBean().getPrincipal();
		if(p != null){
			this.currentUser = getDp().getUser(p.getName());
			this.loadRolesAndProps();
		}else{
			this.currentUser = null;
		}
		return false;
	}
	
	public void listenerSaveUser(ActionEvent event){
		try {
			this.getDp().save(currentUser);
			this.loadRolesAndProps();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addInternalError();
		}
	}
}
