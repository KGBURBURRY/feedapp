package com.bptn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"ImageMetaData\"")
public class ImageMetaData {
	@Id
	@Column(name = "\"imageID\"")
	String imageID;
	@Column(name = "\"imageName\"")
	String imageName;
	@Column(name = "\"imageSize\"")
	String imageSize;
	@Column(name = "\"imageFormat\"")
	String imageFormat;
	@Column(name = "\"imageDate\"")
	String imageDate;
	@Column(name = "resolution")
	String resolution;
	@OneToOne
	@JoinColumn(name = "\"postKey\"")
	Post post;

	public ImageMetaData() {
		super();
		// TODO Auto-generated constructor stub
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "ImageMetaData [imageID=" + imageID + ", imageName=" + imageName + ", imageSize=" + imageSize
				+ ", imageFormat=" + imageFormat + ", imageDate=" + imageDate + ", resolution=" + resolution + ", post="
				+ post + "]";
	}

}