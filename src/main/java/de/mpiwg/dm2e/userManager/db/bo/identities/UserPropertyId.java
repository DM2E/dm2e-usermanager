package de.mpiwg.dm2e.userManager.db.bo.identities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserPropertyId implements Serializable {

	private static final long serialVersionUID = 3664509126411657793L;

	@Column(name="LOGIN", nullable = false, length=250)
	private String userLogin;
	
	@Column(name="NAME", nullable = false, length=250)
	private String name;
	
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
