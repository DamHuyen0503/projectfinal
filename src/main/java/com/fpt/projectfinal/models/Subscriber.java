package com.fpt.projectfinal.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Subscriber")
public class Subscriber {

	@Id
	@Column(name = "subscriberID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subscriberID;

	@Column(name = "subscriberDate")
	private Date subscriberDate;
	
	@Column (name = "status")
	private int status;
	
	@Column (name = "email")
	private String email;
	
	@ManyToMany
	@JoinTable(name = "category_subscriber", joinColumns = @JoinColumn(name = "subscriberID", referencedColumnName = "subscriberID"), 
									 inverseJoinColumns = @JoinColumn(name = "categoryID", referencedColumnName = "categoryID"))
	private Set<Category> categorys;

	public Subscriber() {
	}
	
	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSubscriberID() {
		return subscriberID;
	}

	public void setSubscriberID(int subscriberID) {
		this.subscriberID = subscriberID;
	}

	public Date getSubscriberDate() {
		return subscriberDate;
	}

	public void setSubscriberDate(Date subscriberDate) {
		this.subscriberDate = subscriberDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
