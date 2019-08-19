package com.fpt.projectfinal.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contact")
public class Contact {
	@Id
	@Column(name = "contactID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int contactID;

	@Column(name = "date")
	private Date date;
	
	@Column (name = "content", length = 1000)
	private String content;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "status")
	private int status;

	public Contact() {
	}

	public Contact(int contactID, Date date, String content, String name, String email, int status) {
		super();
		this.contactID = contactID;
		this.date = date;
		this.content = content;
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
