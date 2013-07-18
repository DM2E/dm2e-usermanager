package de.mpiwg.dm2e.userManager.db.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOSSO_ROLE")
public class Role implements Serializable, Cloneable, Comparable<Role>{
	
	private static final long serialVersionUID = -4793823596800578954L;

	@Id
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	
	@Override
	public Object clone(){
		try {
			return (Role)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(Role o) { 
		
		String role1 = this.getName().toLowerCase();
		String role2 = o.getName().toLowerCase();
		
		return role1.compareTo(role2); 
	}
	
}
