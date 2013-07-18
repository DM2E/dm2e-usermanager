package de.mpiwg.dm2e.userManager.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.beans.component.AccountsManager;
import de.mpiwg.dm2e.userManager.beans.component.OwnAccount;
import de.mpiwg.dm2e.userManager.beans.component.Register;
import de.mpiwg.dm2e.userManager.beans.component.RoleEditor;

public class SessionBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -4956838771354937957L;
	
	private static Logger logger = Logger.getLogger(SessionBean.class);
	
	private AccountsManager accountsManager = new AccountsManager();
	private OwnAccount ownAccount = new OwnAccount();
	private Register register = new Register();
	private RoleEditor roleEditor = new RoleEditor();
	
	private List<String> msgList;
	
	public SessionBean(){
		logger.info("#### SessionBean ####");		
	}
	
	public boolean isPrincipalNull(){
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null;
		//return true;
	}
	
	public boolean isAdmin(){
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin");
		//return true;
		//return !isPrincipalNull();
	}
	
	public Principal getPrincipal(){
		Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
				
		return p;
	}
	
	public AccountsManager getAccountsManager() {
		return accountsManager;
	}

	public void addMsg(String m){
		if(msgList == null){
			msgList = new ArrayList<String>();
		}
		msgList.add(m);
	}
	
	public void listenerEmptyMsgList(ActionEvent e){
		this.msgList = null;
	}

	public List<String> getMsgList() {
		return msgList;
	}

	public OwnAccount getOwnAccount() {
		return ownAccount;
	}

	public Register getRegister() {
		return register;
	}

	public RoleEditor getRoleEditor() {
		return roleEditor;
	}

	public void setRoleEditor(RoleEditor roleEditor) {
		this.roleEditor = roleEditor;
	}
	
	
}
