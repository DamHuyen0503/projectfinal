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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Avatar")
	private String avatar;
	
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;
	
	@Column(name = "PhoneNumber")
	private String phoneNumber;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Gender")
	private Integer gender;
	
	@Column(name = "DOB")
	private Date DOB;
	
	
	@OneToOne( optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "email", nullable = false)
    private Account account;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch = FetchType.LAZY)
	private Set<Post> post; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserTest> userTest;
	
	public User() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
		
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public Set<UserTest> getUserTest() {
		return userTest;
	}

	public void setUserTest(Set<UserTest> userTest) {
		this.userTest = userTest;
	}

	
	
}
