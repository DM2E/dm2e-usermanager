package de.mpiwg.dm2e.userManager.beans.component;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.beans.AbstractBean;
import de.mpiwg.dm2e.userManager.db.bo.Role;
import de.mpiwg.dm2e.userManager.db.bo.User;
import de.mpiwg.dm2e.userManager.db.bo.UserProperty;
import de.mpiwg.dm2e.userManager.db.bo.UserRole;
import de.mpiwg.dm2e.userManager.utils.Hashing;
import de.mpiwg.dm2e.userManager.utils.SelectableObject;


public abstract class AccountEditor extends AbstractBean{
	
	private static Logger logger = Logger.getLogger(AccountEditor.class);
	
	protected User currentUser;
	protected String password;
	private String passwordRepeat;
	
	private boolean changePassword = false;
	
	private List<SelectableObject<UserRole>> roleList;
	private List<SelectableObject<UserProperty>> propList;
	
	private List<SelectItem> unusedRoleList;
	private UserRole currentUserRole;
	
	private UserProperty currentUserProp;
	
	protected void loadRolesAndProps(){
		if(currentUser != null){
			this.roleList = new ArrayList<SelectableObject<UserRole>>();
			this.propList = new ArrayList<SelectableObject<UserProperty>>();
			
			for(UserRole us : getDp().getUserRoleByUserLogin(currentUser.getLogin())){
				roleList.add(new SelectableObject<UserRole>(us));
			}
			for(UserProperty up : getDp().getUserPropByUserLogin(currentUser.getLogin())){
				propList.add(new SelectableObject<UserProperty>(up));
			}
			this.loadUnusedRoles();
		}
	}
	
	public void listenerAddUserProp(ActionEvent event){
		this.currentUserProp = new UserProperty();
		this.currentUserProp.getId().setUserLogin(currentUser.getLogin());
	}
	
	public void listenerCancelAddUserProp(ActionEvent event){
		this.currentUserProp = null;
	}
	
	public void listenerSaveUserProp(ActionEvent event){
		try {
			if(isNewUserPropOK()){
				getDp().saveUserProperty(currentUserProp);
				this.loadRolesAndProps();	
			}			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addInternalError();
		}
		this.currentUserProp = null;
	}
	
	private boolean isNewUserPropOK(){
		boolean anwser = true;
		if(StringUtils.isEmpty(this.currentUserProp.getId().getName())){
			addMsg("The name of the property can not be empty.");
			anwser = false;
		}
		
		String propName = this.currentUserProp.getId().getName();
		for(UserProperty us : getDp().getUserPropByUserLogin(currentUser.getLogin())){
			if(StringUtils.equals(propName, us.getId().getName())){
				addMsg("The is already a property called " + propName + ".");
				anwser = false;
			}
		}
		if(!anwser){
			addMsg("The property could not be saved.");
		}
		
		return anwser;
	}
	
	public void listenerAddUserRole(ActionEvent event){
		this.currentUserRole = new UserRole();
		this.currentUserRole.getId().setUserLogin(currentUser.getLogin());
	}
	
	public void listenerCancelAddUserRole(ActionEvent event){
		this.currentUserRole = null;
	}
	
	public void listenerSaveUserRole(ActionEvent event){
		try {
			getDp().saveUserRole(currentUserRole);
			this.loadRolesAndProps();			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addInternalError();
		}
		this.currentUserRole = null;
	}
	
	private void loadUnusedRoles(){
		this.unusedRoleList = new ArrayList<SelectItem>();
		for(Role role : getDp().getRolesUnusedByUser(currentUser.getLogin())){
			this.unusedRoleList.add(new SelectItem(role.getName()));
		}
	}
	
	public List<SelectItem> getunusedRoleList() {
		return unusedRoleList;
	}
	
	public void listenerOpenChangePwdDialog(ActionEvent e){
		this.changePassword = true;
		this.password = null;
		this.passwordRepeat = null;
	}
	
	public void listenerCancelChangePwd(ActionEvent e){
		this.changePassword = false;
		this.password = null;
		this.passwordRepeat = null;
	}
	
	public void listenerSaveNewPwd(ActionEvent event){
		if(isNewPwdOk() && currentUser != null){
			try {
				this.password = Hashing.MD5(password);
				currentUser.setPassword(password);
				getDp().save(currentUser);
				this.changePassword = false;
				this.password = null;
				this.passwordRepeat = null;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				addInternalError();
			}
		}
	}
	
	public void listenerRemoveUserRole(ActionEvent event){
		List<SelectableObject<UserRole>> toDelete = new ArrayList<SelectableObject<UserRole>>();
		for(SelectableObject<UserRole> so : this.roleList){
			if(so.isSelected()){
				toDelete.add(so);
			}
		}
		if(!toDelete.isEmpty()){
			for(SelectableObject<UserRole> so : toDelete){
				try {
					this.roleList.remove(so);
					getDp().remove(so.getObj());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					addInternalError();
				}
			}
			this.loadRolesAndProps();
		}
	}

	public void listenerRemoveUserProp(ActionEvent event){
		List<SelectableObject<UserProperty>> toDelete = new ArrayList<SelectableObject<UserProperty>>();
		for(SelectableObject<UserProperty> so : this.propList){
			if(so.isSelected()){
				toDelete.add(so);
			}
		}
		
		if(!toDelete.isEmpty()){
			for(SelectableObject<UserProperty> so : toDelete){
				try {
					this.roleList.remove(so);
					getDp().remove(so.getObj());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					addInternalError();
				}
			}
			loadRolesAndProps();
		}
	}
	
	protected boolean isNewUserOk(){
		boolean answer = true;
		if(StringUtils.isEmpty(this.currentUser.getLogin())){
			addMsg("Login can not be empty.");
			answer = false;
		}
		
		if(this.getDp().existUserLogin(this.currentUser.getLogin())){
			addMsg("The login " + currentUser.getLogin() + " exists already.");
			answer = false;
		}
		
		if(!isNewPwdOk()){
			addMsg("The passwords are different or are empty.");
			answer = false;
		}
		
		if(!answer){
			addMsg("The account could not be saved.");
		}
		
		return answer;
	}
	
	protected boolean isNewPwdOk(){
		return StringUtils.isNotEmpty(password) && StringUtils.equals(password, passwordRepeat);
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public boolean isChangePassword() {
		return changePassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public List<SelectableObject<UserRole>> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SelectableObject<UserRole>> roleList) {
		this.roleList = roleList;
	}

	public List<SelectableObject<UserProperty>> getPropList() {
		return propList;
	}

	public void setPropList(List<SelectableObject<UserProperty>> propList) {
		this.propList = propList;
	}

	public UserRole getCurrentUserRole() {
		return currentUserRole;
	}

	public void setCurrentUserRole(UserRole currentUserRole) {
		this.currentUserRole = currentUserRole;
	}

	public UserProperty getCurrentUserProp() {
		return currentUserProp;
	}

	public void setCurrentUserProp(UserProperty currentUserProp) {
		this.currentUserProp = currentUserProp;
	}
	
}
