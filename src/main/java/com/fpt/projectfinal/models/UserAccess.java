package com.fpt.projectfinal.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserAccess")
public class UserAccess {

	@Id
	@Column(name = "UserAccessID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userAccessID;
	
	@Column(name = "Status")
	private int status;

	@Column(name = "StartedDate")
	private Date startedDate;

	@Column(name = "FinishedDate")
	private Date finishedDate;
//
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	private User  user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medicalRecordID")
	private MedicalRecord medicalRecord;

	public UserAccess() {
		super();
	}

	public UserAccess(int userAccessID, Integer status, Date startedDate, Date finishedDate, User user,
			MedicalRecord medicalRecord) {
		super();
		this.userAccessID = userAccessID;
		status = status;
		startedDate = startedDate;
		finishedDate = finishedDate;
		this.user = user;
		this.medicalRecord = medicalRecord;
	}

	public int getUserAccessID() {
		return userAccessID;
	}

	public void setUserAccessID(int userAccessID) {
		this.userAccessID = userAccessID;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		startedDate = startedDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		finishedDate = finishedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	
	
	
	
}
