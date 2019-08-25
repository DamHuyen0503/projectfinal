package com.fpt.projectfinal.models;

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
@Table(name = "Answer")
public class Answer {
	@Id
	@Column(name = "AnswerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer answerID;

	@Column(name = "content")
	private String content;
	
	@Column(name = "Score")
	private int score;
	
	@Column(name = "Image")
	private String image;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "questionID")
	private Question question;

	
	
	public Answer() {
		super();
	}
	
	

	



	public Answer(String content, int score, String image, Question question) {
		super();
		this.content = content;
		this.score = score;
		this.image = image;
		this.question = question;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}







	public Integer getAnswerID() {
		return answerID;
	}







	public void setAnswerID(Integer answerID) {
		this.answerID = answerID;
	}
	
	
	
	

}
