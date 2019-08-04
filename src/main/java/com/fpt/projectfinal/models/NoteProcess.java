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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "NoteProcess")
public class NoteProcess {
	@Id
	@Column(name = "NoteID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int noteID;

	@Column(name = "StartTime")
	
	private Date startTime;
	
	@Column (name = "EndTime")
	
	private Date endTime;
	
	@Column (name = "Content")
	private String content;
	
	@Column (name = "Evaluation")
	private int evaluation;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "medicalRecordID")
	private MedicalRecord  medicalRecord;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	private User user;
	
	public NoteProcess() {
		super();
	}

	public NoteProcess(Date startTime, Date endTime, String content, int evaluation) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.evaluation = evaluation;
	}

	public NoteProcess(int noteID, Date startTime, Date endTime, String content, int evaluation) {
		super();
		this.noteID = noteID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.evaluation = evaluation;
	}

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
