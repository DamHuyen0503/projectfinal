package com.fpt.projectfinal.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {

	@Id
	@Column(name = "questionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer questionID;

	@Column(name = "content")
	private String content;
	
	@Column(name = "score")
	private int score;

	@OneToMany(cascade = CascadeType.ALL,mappedBy="question",fetch = FetchType.LAZY)
	private Set<Answer> Answer; 

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="postID")
	private Test test;


	
	public Question() {
		super();
	}


	public Integer getQuestionID() {
		return questionID;
	}


	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public Set<Answer> getAnswer() {
		return Answer;
	}


	public void setAnswer(Set<Answer> answer) {
		Answer = answer;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}



	
	
	
	
	
	
	
}