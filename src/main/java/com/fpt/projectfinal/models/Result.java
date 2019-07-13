package com.fpt.projectfinal.models;

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
@Table(name = "Result")
public class Result {
	@Id
	@Column(name = "resultID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resultID;

	@Column(name = "highestPoint")
	private int highestPoint;

	@Column(name = "lowestPoint")
	private int lowestPoint;
	

	@Column(name = "content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="postID")
	private Test test;

	
	public Result() {
		super();
	}

	public Integer getResultID() {
		return resultID;
	}

	public void setResultID(Integer resultID) {
		this.resultID = resultID;
	}

	public int getHighestPoint() {
		return highestPoint;
	}

	public void setHighestPoint(int highestPoint) {
		this.highestPoint = highestPoint;
	}

	public int getLowestPoint() {
		return lowestPoint;
	}

	public void setLowestPoint(int lowestPoint) {
		this.lowestPoint = lowestPoint;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}


	
}
	