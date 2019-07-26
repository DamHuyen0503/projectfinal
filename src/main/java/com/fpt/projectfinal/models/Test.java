package com.fpt.projectfinal.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Post")
@DiscriminatorValue("2")
public class Test extends Post {

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="test",fetch = FetchType.LAZY)
	private Set<Question> question; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="test",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Result> result;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="test",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserTest> userTest;
	

	public Test() {
		super();
	}

	public Set<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Set<Question> question) {
		this.question = question;
	}

	public Set<Result> getResult() {
		return result;
	}

	public void setResult(Set<Result> result) {
		this.result = result;
	}

	public Set<UserTest> getUserTest() {
		return userTest;
	}

	public void setUserTest(Set<UserTest> userTest) {
		this.userTest = userTest;
	} 
	
	
	
}
