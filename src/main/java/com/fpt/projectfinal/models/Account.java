package com.fpt.projectfinal.models;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@JsonBackReference
	@OneToOne( cascade = CascadeType.ALL, mappedBy = "account")
	private User user;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"), 
									 inverseJoinColumns = @JoinColumn(name = "roleID", referencedColumnName = "roleID"))
	private Set<Role> roles;

	public Account() {
		super();
	}

	
	public Account(String email, String password, User user) {
		super();
		this.email = email;
		this.password = password;
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
