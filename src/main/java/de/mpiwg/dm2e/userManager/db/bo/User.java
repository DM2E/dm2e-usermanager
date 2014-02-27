package de.mpiwg.dm2e.userManager.db.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOSSO_USER")
public class User implements Cloneable, Comparable<User>{
	
	@Id
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="email")
	private String email;
	
	
	@Override
	public Object clone(){
		try {
			return (User)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int compareTo(User o) { 
		
		String login1 = this.login.toLowerCase();
		String login2 = o.getLogin().toLowerCase();
		
		return login1.compareTo(login2); 
	}	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString(){
		return "User [name=" + name + ", login=" + login + "]";
	}

}
