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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Category", uniqueConstraints = { @UniqueConstraint(columnNames = { "categoryID" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
	@Id
	@Column(name = "categoryID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer categoryID;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "status")
	private Integer status;

	@Column(name = "description",columnDefinition="varchar(500)")
	private String description;

	@Column(name = "createdDate", updatable = false)
	private Date createdDate;

	@Column(name = "modifiedDate")
	private Date modifiedDate;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	@JsonIgnore
	private Set<Post> post; 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	@JsonIgnore
	private Set<Subscriber> subscriber;
	
	public Category() {
	}

	public Category(String name, String image, Integer status, String description,
			Date createdDate, Date modifiedDate) {
	
		this.name = name;
		this.image = image;
		this.status = status;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	

	public Category(Integer categoryID, String name, String image, Integer status, String description,
			Date createdDate, Date modifiedDate) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.image = image;
		this.status = status;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public Set<Subscriber> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Set<Subscriber> subscriber) {
		this.subscriber = subscriber;
	}

	
	

	
	
		
}
