package com.bptn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "\"Post\"")

public class Post {

	@Id
	@Column(name = "\"postID\"")
	String postID;

	@Column(name = "\"postType\"")
	String postType;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "\"usernameKey\"")
	UserID userId; // relation with the userid table

	public Post() {
		super();
	}

	public Post(String postID, String postType) {
		super();
		this.postID = postID;
		this.postType = postType;
		// this.userID = userID;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public UserID getUserId() {
		return userId;
	}

	public void setUserId(UserID userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Post [postID=" + postID + ", postType=" + postType + ", userId=" + userId + "]";
	}



}