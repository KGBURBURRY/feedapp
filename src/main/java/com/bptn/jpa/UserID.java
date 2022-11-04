package com.bptn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="\"UserID\"")
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

	public UserID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserID(String username, String name, String emailID, String phoneNumber, String userPassword) {
		super();
		this.username = username;
		this.name = name;
		this.emailID = emailID;
		this.phoneNumber = phoneNumber;
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserID [username=" + username + ", name=" + name + ", emailID=" + emailID + ", phoneNumber="
				+ phoneNumber + ", userPassword=" + userPassword + "]";
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
	
	@Entity
	@Table(name="\"ImageMetaData\"")
	public class ImageMetaData {
		
		@Id
		@Column(name="\"imageID\"")
		String imageID;
		
		@Column(name="\"imageName\"")
		String imageName;
		
		@Column(name="\"imageSize\"")
		String imageSize;
		
		@Column(name="\"imageFormat\"")
		String imageFormat;
		
		@Column(name="\"imageDate\"")
		String imageDate;
		
		@Column(name="resolution")
		String resolution;
		
		@Column(name="\"postKey\"")
		String postKey;

		public ImageMetaData() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ImageMetaData(String imageID, String imageName, String imageSize, String imageFormat, String imageDate,
				String resolution, String postKey) {
			super();
			this.imageID = imageID;
			this.imageName = imageName;
			this.imageSize = imageSize;
			this.imageFormat = imageFormat;
			this.imageDate = imageDate;
			this.resolution = resolution;
			this.postKey = postKey;
		}

		public String getImageID() {
			return imageID;
		}

		public void setImageID(String imageID) {
			this.imageID = imageID;
		}

		public String getImageName() {
			return imageName;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

		public String getImageSize() {
			return imageSize;
		}

		public void setImageSize(String imageSize) {
			this.imageSize = imageSize;
		}

		public String getImageFormat() {
			return imageFormat;
		}

		public void setImageFormat(String imageFormat) {
			this.imageFormat = imageFormat;
		}

		public String getImageDate() {
			return imageDate;
		}

		public void setImageDate(String imageDate) {
			this.imageDate = imageDate;
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}

		public String getPostKey() {
			return postKey;
		}

		public void setPostKey(String postKey) {
			this.postKey = postKey;
		}

		@Override
		public String toString() {
			return "ImageMetaData [imageID=" + imageID + ", imageName=" + imageName + ", imageSize=" + imageSize
					+ ", imageFormat=" + imageFormat + ", imageDate=" + imageDate + ", resolution=" + resolution
					+ ", postKey=" + postKey + "]";
		}
	
	}
}
