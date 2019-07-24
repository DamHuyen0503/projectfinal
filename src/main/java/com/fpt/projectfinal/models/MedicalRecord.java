	package com.fpt.projectfinal.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MedicalRecord")
public class MedicalRecord {
	@Id
	@Column(name = "medicalRecordID", nullable = false)
	private String medicalRecordID;
	
	@Column(name = "CreatedDate", updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name = "Status")
	private int status;

	@Column(name = "PyschologyType")
	private String pyschologyType;

	@Column(name = "ModifiedDate")
	private Date modifiedDate;

	@Column(name = "Job")
	private String job;

	@Column(name = "CurrentAddress")
	private String currentAddress;

	@Column(name = "NoteOfRelatives")
	private String noteOfRelatives;

	@Column(name = "RelativesPhone")
	private String relativesPhone;

	@Column(name = "RelativesRelationship")
	private String relativesRelationship;

	@Column(name = "RelativesName")
	private String relativesName;

	@Column(name = "OtherStatus")
	private String otherStatus;

	@Column(name = "LearningResult")
	private String learningResult;

	@Column(name = "Health")
	private String health;

	@Column(name = "FriendStatus")
	private String friendStatus;

	@Column(name = "FamilyStatus")
	private String familyStatus;

	@Column(name = "SolutionInPast")
	private String solutionInPast;

	@Column(name = "ReactHistory")
	private String reactHistory;

	@Column(name = "ProblemProcess")
	private String problemProcess;

	@Column(name = "AroundReact")
	private String aroundReact;

	@Column(name = "FeelingHistory")
	private String feelingHistory;

	@Column(name = "Reason")
	private String reason;

	@Column(name = "CurrentProblem")
	private String currentProblem;

	@Column(name = "AppearanceOfProblem")
	private String appearanceOfProblem;

	@Column(name = "Strength")
	private String strength;

	@Column(name = "Resource")
	private String resource;

	@Column(name = "Obstacle")
	private String obstacle;

	@Column(name = "Cause")
	private String cause;

	@Column(name = "Problem")
	private String problem;

	@Column(name = "Method")
	private String method;

	@Column(name = "Processing")
	private String processing;

	@Column(name = "Result")
	private String result;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "clientID")
	
	private Client client;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medicalRecord")
	private Set<NoteProcess> noteProcess; 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicalRecord")
	@JsonIgnore
	private Set<UserAccess> userAccess; 
	
	
	public MedicalRecord() {
		super();
	}

	public MedicalRecord(String medicalRecordID, Date createDate, int status, String pyschologyType, Date modifiedDate,
			String job, String currentAddress, String noteOfRelatives, String relativesPhone,
			String relativesRelationship, String relativesName, String otherStatus, String learningResult,
			String health, String friendStatus, String familyStatus, String solutionInPast, String reactHistory,
			String problemProcess, String aroundReact, String feelingHistory, String reason, String currentProblem,
			String appearanceOfProblem, String strength, String resource, String obstacle, String cause, String problem,
			String method, String processing, String result, Client client) {
		super();
		this.medicalRecordID = medicalRecordID;
		this.createDate = createDate;
		this.status = status;
		this.pyschologyType = pyschologyType;
		this.modifiedDate = modifiedDate;
		this.job = job;
		this.currentAddress = currentAddress;
		this.noteOfRelatives = noteOfRelatives;
		this.relativesPhone = relativesPhone;
		this.relativesRelationship = relativesRelationship;
		this.relativesName = relativesName;
		this.otherStatus = otherStatus;
		this.learningResult = learningResult;
		this.health = health;
		this.friendStatus = friendStatus;
		this.familyStatus = familyStatus;
		this.solutionInPast = solutionInPast;
		this.reactHistory = reactHistory;
		this.problemProcess = problemProcess;
		this.aroundReact = aroundReact;
		this.feelingHistory = feelingHistory;
		this.reason = reason;
		this.currentProblem = currentProblem;
		this.appearanceOfProblem = appearanceOfProblem;
		this.strength = strength;
		this.resource = resource;
		this.obstacle = obstacle;
		this.cause = cause;
		this.problem = problem;
		this.method = method;
		this.processing = processing;
		this.result = result;
		this.client = client;
	}
	public Set<NoteProcess> getNoteProcess() {
		return noteProcess;
	}

	public void setNoteProcess(Set<NoteProcess> noteProcess) {
		this.noteProcess = noteProcess;
	}

	

	public Set<UserAccess> getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(Set<UserAccess> userAccess) {
		this.userAccess = userAccess;
	}

	public String getMedicalRecordID() {
		return medicalRecordID;
	}

	public void setMedicalRecordID(String medicalRecordID) {
		this.medicalRecordID = medicalRecordID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPyschologyType() {
		return pyschologyType;
	}

	public void setPyschologyType(String pyschologyType) {
		this.pyschologyType = pyschologyType;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getNoteOfRelatives() {
		return noteOfRelatives;
	}

	public void setNoteOfRelatives(String noteOfRelatives) {
		this.noteOfRelatives = noteOfRelatives;
	}

	public String getRelativesPhone() {
		return relativesPhone;
	}

	public void setRelativesPhone(String relativesPhone) {
		this.relativesPhone = relativesPhone;
	}

	public String getRelativesRelationship() {
		return relativesRelationship;
	}

	public void setRelativesRelationship(String relativesRelationship) {
		this.relativesRelationship = relativesRelationship;
	}

	public String getRelativesName() {
		return relativesName;
	}

	public void setRelativesName(String relativesName) {
		this.relativesName = relativesName;
	}

	public String getOtherStatus() {
		return otherStatus;
	}

	public void setOtherStatus(String otherStatus) {
		this.otherStatus = otherStatus;
	}

	public String getLearningResult() {
		return learningResult;
	}

	public void setLearningResult(String learningResult) {
		this.learningResult = learningResult;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(String friendStatus) {
		this.friendStatus = friendStatus;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public String getSolutionInPast() {
		return solutionInPast;
	}

	public void setSolutionInPast(String solutionInPast) {
		this.solutionInPast = solutionInPast;
	}

	public String getReactHistory() {
		return reactHistory;
	}

	public void setReactHistory(String reactHistory) {
		this.reactHistory = reactHistory;
	}

	public String getProblemProcess() {
		return problemProcess;
	}

	public void setProblemProcess(String problemProcess) {
		this.problemProcess = problemProcess;
	}

	public String getAroundReact() {
		return aroundReact;
	}

	public void setAroundReact(String aroundReact) {
		this.aroundReact = aroundReact;
	}

	public String getFeelingHistory() {
		return feelingHistory;
	}

	public void setFeelingHistory(String feelingHistory) {
		this.feelingHistory = feelingHistory;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCurrentProblem() {
		return currentProblem;
	}

	public void setCurrentProblem(String currentProblem) {
		this.currentProblem = currentProblem;
	}

	public String getAppearanceOfProblem() {
		return appearanceOfProblem;
	}

	public void setAppearanceOfProblem(String appearanceOfProblem) {
		this.appearanceOfProblem = appearanceOfProblem;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getObstacle() {
		return obstacle;
	}

	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
