package com.fpt.projectfinal.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Integer Status;

	@Column(name = "StartedDate", updatable = false)
	private Date StartedDate;

	@Column(name = "FinishedDate")
	private Date FinishedDate;
//
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	private User  user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "medicalRecordID")
	private MedicalRecord medicalRecord;

	public UserAccess() {
		super();
	}

	public UserAccess(int userAccessID, Integer status, Date startedDate, Date finishedDate, User user,
			MedicalRecord medicalRecord) {
		super();
		this.userAccessID = userAccessID;
		Status = status;
		StartedDate = startedDate;
		FinishedDate = finishedDate;
		this.user = user;
		this.medicalRecord = medicalRecord;
	}

	public int getUserAccessID() {
		return userAccessID;
	}

	public void setUserAccessID(int userAccessID) {
		this.userAccessID = userAccessID;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Date getStartedDate() {
		return StartedDate;
	}

	public void setStartedDate(Date startedDate) {
		StartedDate = startedDate;
	}

	public Date getFinishedDate() {
		return FinishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		FinishedDate = finishedDate;
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
