package com.foody.dto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.foody.entities.Degree;
import com.foody.entities.Faculty;
import com.foody.entities.enums.UserGender;

public class DoctorRegisterRequest {

	@Size(min = 1, max = 30)
	@NotNull(message = "Please provide a fullname")
	private String fullName;
	
	@NotNull(message = "Please provide a TokenCode")
	private String tokenCode;

	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	@Size(max = 100)
	private String address;
	
	@Size(max = 20)
	private String mobile;
	
	private String about;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private Set<Degree> degrees = new HashSet<>();
	
	public DoctorRegisterRequest() {
		
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public Set<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(Set<Degree> degrees) {
		this.degrees = degrees;
	}
}
