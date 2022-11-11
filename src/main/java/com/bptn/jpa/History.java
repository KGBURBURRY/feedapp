package com.bptn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="\"History\"")
public class History {
	
	@Id
	@Column(name="\"postID\"")
	String postID;
	
	@Column(name="date")
	String date;
	
	@Column(name="\"postType\"")
	String postType;
	
	@Column(name="\"postUpload\"")
	String postUpload;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "\"usernameKey\"")
	private UserID userId;


	public String getPostID() {
		return postID;
	}


	public void setPostID(String postID) {
		this.postID = postID;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getPostType() {
		return postType;
	}


	public void setPostType(String postType) {
		this.postType = postType;
	}


	public String getPostUpload() {
		return postUpload;
	}


	public void setPostUpload(String postUpload) {
		this.postUpload = postUpload;
	}


	public UserID getUserId() {
		return userId;
	}


	public void setUserId(UserID userId) {
		this.userId = userId;
	}


	public History() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "History [postID=" + postID + ", date=" + date + ", postType=" + postType + ", postUpload=" + postUpload
				+ ", userId=" + userId + "]";
	}


}