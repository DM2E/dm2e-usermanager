package de.mpiwg.dm2e.userManager.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import de.mpiwg.dm2e.userManager.db.bo.Role;
import de.mpiwg.dm2e.userManager.db.bo.User;
import de.mpiwg.dm2e.userManager.db.bo.UserProperty;
import de.mpiwg.dm2e.userManager.db.bo.UserRole;
import de.mpiwg.dm2e.userManager.utils.HibernateUtil;
import de.mpiwg.dm2e.userManager.utils.map.duplex.DuplexMap;

public abstract class AbstractDataProvider {
	
	private static Logger logger = Logger.getLogger(AbstractDataProvider.class);

	//<login, user>
	private Map<String, User> userMap;
	
	//<name, role>
	private Map<String, Role> roleMap;
	
	private DuplexMap<UserProperty> userPropMap;
	
	private DuplexMap<UserRole> userRoleMap;
	
	protected Map<String, User> getUserMap(){
		if(userMap == null){
			logger.info("Loading Users from DB");
			userMap = new HashMap<String, User>();
			for(User user : getUserListFromDB()){
				userMap.put(user.getLogin(), user);
			}
		}
		return userMap;
	}
	
	protected Map<String, Role> getRoleMap(){
		if(roleMap == null){
			logger.info("Loading Roles from DB");
			roleMap = new HashMap<String, Role>();
			for(Role role : getRoleListFromDB()){
				roleMap.put(role.getName(), role);
			}
		}
		return roleMap;
	}
	
	protected DuplexMap<UserProperty> getUserPropMap(){
		if(userPropMap == null){
			logger.info("Loading UserProperties from DB");
			userPropMap = new DuplexMap<UserProperty>();
			for(UserProperty up : getUserPropertyListFromDB()){
				userPropMap.put(up.getKey(), up);
			}
		}
		return userPropMap;
	}
	
	protected DuplexMap<UserRole> getUserRoleMap(){
		if(userRoleMap == null){
			logger.info("Loading UserRole from DB");
			userRoleMap = new DuplexMap<UserRole>();
			for(UserRole ur : getUserRoleListFromDB()){
				userRoleMap.put(ur.getKey(), ur);
			}
		}
		return userRoleMap;
	}
	
	public void remove(Object obj) throws Exception{
		logger.info("Trying to remove " + obj);
		Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		
		session.delete(obj);
		
		session.getTransaction().commit();
		
		if(obj instanceof User){
			getUserMap().remove(((User) obj).getLogin());
		}else if(obj instanceof Role){
			getRoleMap().remove(((Role) obj).getName());
		}else if(obj instanceof UserRole){
			getUserRoleMap().remove(((UserRole) obj).getKey());
		}else if(obj instanceof UserProperty){
			getUserPropMap().remove(((UserProperty) obj).getKey());
		}
		logger.info("Removed " + obj);
	}
	
	public void save(User user) throws Exception{
		
		Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		
		session.saveOrUpdate(user);
		
		session.getTransaction().commit();
		getUserMap().put(user.getLogin(), user);
		logger.info("Saved=" + user);		
	}
	
	public void save(Role role){
		try {
			Session session = 
					HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			
			session.saveOrUpdate(role);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally{
			this.getRoleMap().put(role.getName(), role);
			logger.info("Saved=" + role);
		}
	}
	
	public void saveUserProperty(UserProperty userProp) throws Exception{
		Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		
		session.saveOrUpdate(userProp);
		
		session.getTransaction().commit();
		this.getUserPropMap().put(userProp.getKey(), userProp);
		logger.info("Saved=" + userProp);
		
	}
	
	public void saveUserRole(UserRole userRole) throws Exception{
		Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		
		session.saveOrUpdate(userRole);
		
		session.getTransaction().commit();
		this.userRoleMap.put(userRole.getKey(), userRole);
		logger.info("Saved=" + userRole);		
	}
	
	private List<User> getUserListFromDB(){
		List<User> list = null;
		try {
			Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from User");
			list = query.list();		
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	private List<Role> getRoleListFromDB(){
		List<Role> list = null;
		try {
			Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from Role");
			list = query.list();		
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	private List<UserProperty> getUserPropertyListFromDB(){
		List<UserProperty> list = null;
		try {
			Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from UserProperty");
			list = query.list();		
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	private List<UserRole> getUserRoleListFromDB(){
		List<UserRole> list = null;
		try {
			Session session = 
				HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from UserRole");
			list = query.list();		
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
}
