package de.mpiwg.dm2e.userManager.db.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mpiwg.dm2e.userManager.db.bo.identities.UserPropertyId;
import de.mpiwg.dm2e.userManager.utils.map.duplex.DuplexKey;

@Entity
@Table(name="JOSSO_USER_PROPERTY")
public class UserProperty implements Serializable{
	
	private static final long serialVersionUID = -701406027305492553L;

	@EmbeddedId UserPropertyId id;
	
	@Column(name="VALUE", nullable = false, length=250)
	private String value;
	
	public UserProperty(){
		this.id = new UserPropertyId();
	}
	
	public UserPropertyId getId() {
		return id;
	}
	public void setId(UserPropertyId id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public DuplexKey getKey(){
		return new DuplexKey(id.getUserLogin(), id.getName());
	}
	
	
}
