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
import de.mpiwg.dm2e.userManager.db.bo.identities.UserPropertyId;


public class DataProvider extends AbstractDataProvider{
	
	private static Logger logger = Logger.getLogger(DataProvider.class);
	
	public DataProvider(){
		logger.info("***************");
		logger.info("DataProvider");
		logger.info("***************");
	}
	
	@Override
	public void save(User user) throws Exception{
		super.save(user);
		
		//check if there is a property called email (required by josso).
		UserProperty prop = getUserPropByUserLogin(user.getLogin(), "email");
		if(prop == null){
			prop = new UserProperty();
			UserPropertyId id = new UserPropertyId();
			id.setName("email");
			id.setUserLogin(user.getLogin());
			
			prop.setValue(user.getEmail());
			prop.setId(id);
			this.saveUserProperty(prop);
			
		}
		
	}
	
	public boolean existUserLogin(String login){
		return (getUser(login) != null);		
	}
	
	public boolean existRoleName(String roleName){
		return this.getRoleMap().containsKey(roleName);
	}
	
	
	public Collection<UserProperty> getUserPropByUserLogin(String login){
		return this.getUserPropMap().getValuesByFirstKey(login);
	}
	
	public UserProperty getUserPropByUserLogin(String login, String propName){
		Collection<UserProperty> list = getUserPropMap().getValuesByFirstKey(login);
		if(list != null){
			for(UserProperty prop : list){
				if(prop.getKey().equals(propName)){
					return prop;
				}
			}	
		}
		return null;
	}
	
	public Collection<UserRole> getUserRoleByUserLogin(String login){
		return this.getUserRoleMap().getValuesByFirstKey(login);
	}
	
	public Collection<Role> getRoles(){
		return getRoleMap().values();
	}
	
	public Role getRoleByName(String roleName){
		return getRoleMap().get(roleName);
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
