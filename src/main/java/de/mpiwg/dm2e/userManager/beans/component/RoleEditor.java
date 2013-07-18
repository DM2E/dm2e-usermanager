package de.mpiwg.dm2e.userManager.beans.component;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import de.mpiwg.dm2e.userManager.beans.AbstractBean;
import de.mpiwg.dm2e.userManager.db.bo.Role;

public class RoleEditor extends AbstractBean{
	
	private Role currentRole;
	private boolean create = false;
	
	public void listenerCreateRole(ActionEvent event){
		this.currentRole = new Role();
		this.create = true;
	}
	
	public void listenerSelectRole(ActionEvent event){
		Role role = (Role)getRequestBean("role");
		if(role != null){
			this.currentRole = (Role)role.clone();
			create = false;
		}
	}
	
	public void listenerCancel(ActionEvent event){
		this.currentRole = null;
		this.create = false;
	}
	
	public void listenerSaveRole(ActionEvent event){
		try {
			
			if(create){
				if(isNewRoleOk()){
					getDp().save(currentRole);
					create = false;	
				}
			}else{
				getDp().save(currentRole);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addMsg(e.getMessage());
		}
	}
	
	protected boolean isNewRoleOk(){
		
		boolean response = true;
		
		if(StringUtils.isEmpty(currentRole.getName())){
			addMsg("Role name can not be empty.");
			response = false;
		}
		
		if(getDp().existRoleName(currentRole.getName())){
			addMsg("Role name exists already.");
			response = false;
		}
			
		if(!response){
			addMsg("The role could not be saved.");
		}
			
		return response;
		
	}
	
	public Role getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	public boolean isCreate() {
		return create;
	}
	

}
