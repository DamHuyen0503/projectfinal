package com.fpt.projectfinal.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Slider",uniqueConstraints = { @UniqueConstraint(columnNames = { "sliderID" }) })
public class Slider {
	
	@Id
	@Column(name = "sliderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sliderID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "modifiedDate")
	private Date modifiedDate;
	
	public Slider() {
		
	}
	
	public Slider(String name, String image, String link, Date createdDate, Date modifiedDate) {
		this.name = name;
		this.image = image;
		this.link = link;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getSliderID() {
		return sliderID;
	}

	public void setSliderID(Integer sliderID) {
		this.sliderID = sliderID;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
}
