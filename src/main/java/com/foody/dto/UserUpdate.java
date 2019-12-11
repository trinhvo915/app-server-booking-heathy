package com.foody.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

public class UserUpdate {
	
	private String id;
	
	@Type(type="text")
	private String about;
	
	@Size(max = 20)
	private String mobile;
	
	@Size(max = 100)
	private String address;
	
	private String fullName;
	
	private Date birthday;
	
	@Size(max = 100)
	private String facebook;

	
	public UserUpdate() {
		super();
	}

	public UserUpdate(String id, String about, @Size(max = 20) String mobile, @Size(max = 100) String address,
			String fullName, Date birthday, @Size(max = 100) String facebook) {
		super();
		this.id = id;
		this.about = about;
		this.mobile = mobile;
		this.address = address;
		this.fullName = fullName;
		this.birthday = birthday;
		this.facebook = facebook;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public String getFullName() {
		return fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
}
