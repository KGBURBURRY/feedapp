package com.bptn.jpa;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="\"UserID\"")
public class UserID {
	@Id
	@Column(name="username")
	String username;
	
	@Column(name="name")
	String name;
	
	@Column(name="\"emailID\"")
	String emailID;
	
	@Column(name="\"phoneNumber\"")
	String phoneNumber;
	
	@Column(name="\"userPassword\"")
	String userPassword;
	
	@OneToMany(mappedBy = "userId")//
	private List<Post> posts;
	
	@OneToMany(mappedBy = "userId")//
	private List<History> history;



	public UserID() {
		super();
		
	}

	public UserID(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "UserID [username=" + username + ", name=" + name + ", emailID=" + emailID + ", phoneNumber="
				+ phoneNumber + ", userPassword=" + userPassword + ", posts=" + posts + "]";
	

	
}
}


