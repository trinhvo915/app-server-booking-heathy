package com.foody.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.foody.entities.Attachment;
import com.foody.entities.Degree;
import com.foody.entities.Faculty;
import com.foody.entities.enums.UserGender;

public class UserResponceClinic {
	private String id;
	
	private Date createAt;
	
	private Date updateAt;
	
	private String createdBy;
	
	private String updatedBy  ;
	
	private String deletedBy ;
	
	private String username;
	
	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	private Integer badPoint;
	
	private String email;
	
	private String address;
	
	private String mobile;
	
	private String about;
	
	private String facebook;
	
	private String code;
	
	private Attachment attachmentPerson ;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private List<BookingResponse> bookingExperts = new ArrayList<BookingResponse>() ;
	
	private List<CommentResponse> commentExperts = new ArrayList<CommentResponse>();
	
	private Set<Degree> degrees = new HashSet<>();
	
	private List<RateResponse> rateResponses = new ArrayList<RateResponse>();
	
	private List<String> dateBookingDoctors = new ArrayList<String>();
	
	private Double countRate;
	
	public UserResponceClinic() {
		
	}

	public String getId() {
		return id;
	}

	public List<String> getDateBookingDoctors() {
		return dateBookingDoctors;
	}

	public void setDateBookingDoctors(List<String> dateBookingDoctors) {
		this.dateBookingDoctors = dateBookingDoctors;
	}

	public List<RateResponse> getRateResponses() {
		return rateResponses;
	}

	public void setRateResponses(List<RateResponse> rateResponses) {
		this.rateResponses = rateResponses;
	}

	public Double getCountRate() {
		return countRate;
	}

	public void setCountRate(Double countRate) {
		this.countRate = countRate;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public UserGender getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getBadPoint() {
		return badPoint;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAbout() {
		return about;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getCode() {
		return code;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public List<BookingResponse> getBookingExperts() {
		return bookingExperts;
	}

	public List<CommentResponse> getCommentExperts() {
		return commentExperts;
	}

	public Set<Degree> getDegrees() {
		return degrees;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBadPoint(Integer badPoint) {
		this.badPoint = badPoint;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public void setBookingExperts(List<BookingResponse> bookingExperts) {
		this.bookingExperts = bookingExperts;
	}

	public void setCommentExperts(List<CommentResponse> commentExperts) {
		this.commentExperts = commentExperts;
	}

	public void setDegrees(Set<Degree> degrees) {
		this.degrees = degrees;
	}

	public Attachment getAttachmentPerson() {
		return attachmentPerson;
	}

	public void setAttachmentPerson(Attachment attachmentPerson) {
		this.attachmentPerson = attachmentPerson;
	}
}
