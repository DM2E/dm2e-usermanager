package de.mpiwg.dm2e.userManager.beans;

import javax.faces.context.FacesContext;

import de.mpiwg.dm2e.userManager.db.DataProvider;

public abstract class AbstractBean {

	public Object getRequestBean(String name){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(name);
	}
	
	public ApplicationBean getAppBean(){
		//@TODO null???
		return (ApplicationBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("appBean");
	}
	
	public SessionBean getSessionBean(){
		//@TODO null???
		return (SessionBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionBean");
	}
	
	public void addMsg(String m){
		this.getSessionBean().addMsg(m);
	}
	
	public DataProvider getDp(){
		return getAppBean().getDp();
	}
}
