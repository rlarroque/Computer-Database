package com.excilys.computer_database.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This class describe a simple user with a username and password
 * @author rlarroque
 *
 */
@Entity(name = "user")
public class User  implements Serializable{
	
	private static final long serialVersionUID = 8820300483561441108L;
	
	@Id
	@Column(unique = true, nullable = false, length = 100)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
}
