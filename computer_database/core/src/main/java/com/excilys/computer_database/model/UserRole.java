package com.excilys.computer_database.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -5268461116279169660L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
	private Integer userroleId;
	
	@ManyToOne
    @JoinColumn(name = "username", nullable = false)
	private User user;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	public Integer getUserroleId() {
		return userroleId;
	}
	
	public void setUserroleId(Integer userroleId) {
		this.userroleId = userroleId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}	
}
