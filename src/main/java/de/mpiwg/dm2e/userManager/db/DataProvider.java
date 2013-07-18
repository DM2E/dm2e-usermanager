package de.mpiwg.dm2e.userManager.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.mpiwg.dm2e.userManager.db.bo.Role;
import de.mpiwg.dm2e.userManager.db.bo.User;
import de.mpiwg.dm2e.userManager.db.bo.UserProperty;
import de.mpiwg.dm2e.userManager.db.bo.UserRole;


public class DataProvider extends AbstractDataProvider{
	
	private static Logger logger = Logger.getLogger(DataProvider.class);
	
	public DataProvider(){
		logger.info("***************");
		logger.info("DataProvider");
		logger.info("***************");
	}
	
	public boolean existUserLogin(String login){
		return this.getUserMap().containsKey(login);
	}
	
	public boolean existRoleName(String roleName){
		return this.getRoleMap().containsKey(roleName);
	}
	
	
	public Collection<UserProperty> getUserPropByUserLogin(String login){
		return this.getUserPropMap().getValuesByFirstKey(login);
	}
	
	public Collection<UserRole> getUserRoleByUserLogin(String login){
		return this.getUserRoleMap().getValuesByFirstKey(login);
	}
	
	public Collection<User> getUsers(){
		return getUserMap().values();
	}
	
	public User getUser(String login){
		return getUserMap().get(login);
	}
	
	
	public Collection<Role> getRoles(){
		return getRoleMap().values();
	}
	
	public List<Role> getRolesUnusedByUser(String userLogin){
		List<Role> rs = new ArrayList<Role>();
		
		Collection<UserRole> userRoleList = getUserRoleByUserLogin(userLogin);
		for(Role role : getRoleMap().values()){
			if(!listContainsRole(userRoleList, role)){
				rs.add(role);
			}
		}
		return rs;
	}
	
	private static boolean listContainsRole(Collection<UserRole> list, Role role){
		
		for(UserRole ur : list){
			if(StringUtils.equals(ur.getId().getRoleName(), role.getName())){
				return true;
			}
		}
		return false;
	}
	
}
