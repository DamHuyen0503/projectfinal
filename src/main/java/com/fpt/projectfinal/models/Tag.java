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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tag", uniqueConstraints = { @UniqueConstraint(columnNames = { "tagID" }) })

public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagID")
	private Integer tagID;

	@Column(name = "content", unique = true)
	private String content;

	@Column(name = "createDate", updatable = false)
	private Date createdDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "post_tag", joinColumns = {
			@JoinColumn(name = "tagID", nullable = true, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "postID", nullable = true, updatable = true) })
	private Set<Post> post;
	
	
	
	
	
	public Tag() {
		super();
	}
	
	
	

	public Tag(String content, Date createdDate) {
		super();
		this.content = content;
		this.createdDate = createdDate;
	}




	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Set<Post> getPost() {
		return post;
	}
	
	public void setPost(Set<Post> post) {
		this.post = post;
	}
	
	public Integer getTagID() {
		return tagID;
	}
	
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	
	 
	
}
