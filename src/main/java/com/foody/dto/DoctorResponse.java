package com.foody.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.foody.entities.Clinic;
import com.foody.entities.Degree;
import com.foody.entities.Faculty;
import com.foody.entities.enums.UserGender;

public class DoctorResponse {
	
	private String id;

	private Date createAt;
	
	private Date updateAt;
	
	private String createdBy;
	
	private String updatedBy  ;
	
	private String deletedBy ;
	
	@Column(name = "fullname")
	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	private String email;
	
	@Size(max = 100)
	private String address;
	
	@Size(max = 20)
	private String mobile;
	
	@Type(type="text")
	private String about;
	
	@Size(max = 100)
	private String facebook;
	
	private Clinic clinic ;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private Set<Degree> degrees = new HashSet<>();

	public DoctorResponse(String id, Date createAt, Date updateAt, String createdBy, String updatedBy, String deletedBy, 
			String fullName, Date birthday, UserGender gender, Integer age,
			String email, @Size(max = 100) String address, @Size(max = 20) String mobile, String about,
			@Size(max = 100) String facebook, Clinic clinic, Set<Faculty> faculties, Set<Degree> degrees) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.deletedBy = deletedBy;
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
		this.about = about;
		this.facebook = facebook;
		this.clinic = clinic;
		this.faculties = faculties;
		this.degrees = degrees;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
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
