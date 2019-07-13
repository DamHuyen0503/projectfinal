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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Client")
public class Client {
	@Id
	@Column(name = "ClientID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int clientID;

	@Column(name = "DOB")
	private Date dob;
	
	@Column (name = "Gender")
	private int gender;
	
	@Column (name = "Address")
	private String address;
	
	@Column (name = "Email")
	private String email;
	
	@Column (name = "CreatedDate")
	private Date createdDate;
	
	@Column (name = "PhoneNumber")
	private String phoneNumber;
	
	@Column (name = "Note")
	private String note;

	@Column (name = "Alias")
	private String alias;
	

	@Column (name = "SSN")
	private String ssn;

	@Column (name = "FullName")
	private String fullName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
	@JsonIgnore
	private Set<MedicalRecord> medicalRecord; 
	
	public Client() {
		super();
	}

	public Client(int clientID, Date dob, int gender, String address, String email, Date createdDate,
			String phoneNumber, String note, String alias, String ssn, String fullName) {
		super();
		this.clientID = clientID;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.createdDate = createdDate;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.alias = alias;
		this.ssn = ssn;
		this.fullName = fullName;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
