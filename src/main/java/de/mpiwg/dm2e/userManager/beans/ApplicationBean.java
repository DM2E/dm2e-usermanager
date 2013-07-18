package de.mpiwg.dm2e.userManager.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.db.DataProvider;
import de.mpiwg.dm2e.userManager.db.bo.Role;
import de.mpiwg.dm2e.userManager.db.bo.User;

//@ManagedBean(name= "app")
//@ViewScoped
public class ApplicationBean implements Serializable {
	private static final long serialVersionUID = 3967168459837443473L;
	
	private static Logger logger = Logger.getLogger(ApplicationBean.class);
	
	public DataProvider dp;
	
	
	public ApplicationBean(){
		logger.info("***************");
		logger.info("ApplicationBean");
		logger.info("***************");
		
		try{
			int mb = 1024*1024;
			 
	        //Getting the runtime reference from system
	        Runtime runtime = Runtime.getRuntime();
	 
	        logger.info("##### Heap utilization statistics [MB] #####");
	 
	        //Print used memory
	        logger.info("Used Memory:"
	            + (runtime.totalMemory() - runtime.freeMemory()) / mb);
	 
	        //Print free memory
	        logger.info("Free Memory:"
	            + runtime.freeMemory() / mb);
	 
	        //Print total available memory
	        logger.info("Total Memory:" + runtime.totalMemory() / mb);
	 
	        //Print Maximum available memory
	        logger.info("Max Memory:" + runtime.maxMemory() / mb + "\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		this.dp = new DataProvider();		
	}
	
	public String getLoginUrl(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/josso_user_login/";
	}
	
	public String getLogoutUrl(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/josso_logout/";
	}
	
	public DataProvider getDp(){
		return dp;
	}
	
	public List<User> getUserList(){
		List<User> list = new ArrayList<User>(dp.getUsers());
		Collections.sort(list);
		return list;
	}
	
	public List<Role> getRoleList(){
		List<Role> list = new ArrayList<Role>(dp.getRoles());
		Collections.sort(list);
		return list;
	}

	
	public String getRequestContextPath(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	public String getJSConfirmationSave(){
		return "if(!confirm('Do you really want to save the changes?')){ return false; };";
	}
	
	public String getJSConfirmationDelete(){
		return "if(!confirm('Do you really want to detele this?')){ return false; };";
	}
	
	public String getJSConfirmationSaveAsNew(){
		return "if(!confirm('Do you really want to save the entity as new?')){ return false;};";
	}
	
	public String getJSConfirmationCleanForm(){
		return "if(!confirm('Do you really want to clear the form?')){ return false;};";
	}
	
}
