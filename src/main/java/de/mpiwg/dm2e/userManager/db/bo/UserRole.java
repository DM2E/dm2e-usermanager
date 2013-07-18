package de.mpiwg.dm2e.userManager.db.bo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import de.mpiwg.dm2e.userManager.db.bo.identities.UserRoleId;
import de.mpiwg.dm2e.userManager.utils.map.duplex.DuplexKey;

@Entity
@Table(name="JOSSO_USER_ROLE")
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = -302522875224752569L;
	
	@EmbeddedId UserRoleId id;
	
	public UserRole(){
		this.id = new UserRoleId();
	}

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}
	
	public DuplexKey getKey(){
		return new DuplexKey(id.getUserLogin(), id.getRoleName());
	}
	
	
	/*
	@Id
	@Column(name="LOGIN")
	private String userLogin;
	
	@Id
	@Column(name="NAME")
	private String roleName;
	
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	*/
	
}
