package de.mpiwg.dm2e.userManager.db.bo.identities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleId implements Serializable{
	private static final long serialVersionUID = 3430596628751632498L;

	@Column(name="LOGIN", nullable = false, length=250)
	private String userLogin;
	
	@Column(name="NAME", nullable = false, length=250)
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
	
}
