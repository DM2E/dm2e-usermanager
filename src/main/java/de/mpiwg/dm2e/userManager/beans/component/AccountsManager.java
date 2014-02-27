package de.mpiwg.dm2e.userManager.beans.component;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.db.bo.User;

public class AccountsManager extends AccountEditor {

	private static Logger logger = Logger.getLogger(AccountsManager.class);
	private boolean create = false;
	
	
	@Override
	protected void loadRolesAndProps(){
		if(getCurrentUser() != null && !create){
			super.loadRolesAndProps();
		}
	}


	
	public void listenerSelectUser(ActionEvent event){
		this.currentUser = (User)getRequestBean("user");
		this.create = false;
		if(currentUser != null){
			this.currentUser = (User)currentUser.clone();
			this.loadRolesAndProps();
			logger.info(currentUser);
		}
	}
	
	public void listenerCreateUser(ActionEvent event){
		this.currentUser = new User();
		this.create = true;
	}
	
	public void listenerCancelEdition(ActionEvent event){
		this.currentUser = null;
		this.create = false;
	}
	
	public void listenerSaveUser(ActionEvent event){
		try {
			if(create){
				if(isNewUserOk()){
					this.currentUser.setPassword(password);
					this.getDp().save(currentUser);
					this.create = false;	
					this.loadRolesAndProps();
				}
			}else{
				this.getDp().save(currentUser);
				this.loadRolesAndProps();
			}	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addInternalError();
		}
	}
	
	public boolean isCreate(){
		return create;
	}

	
	
}
