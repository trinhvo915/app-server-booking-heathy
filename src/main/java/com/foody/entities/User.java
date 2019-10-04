package com.foody.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.foody.entities.enums.UserGender;

@Entity
@Table(name = "user")
public class User extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	@NotNull(message = "Please provide a username")
//    @Column(unique = true)
	private String username;
	
//	@NotNull(message = "Please provide a pasword")
//    @Column(unique = true)
	private String password;
	
	@Column(name = "fullname")
	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	@Column(name="bad_point")
	private Integer badPoint;
	
//	@Size(max = 100)
//	@Column(unique = true)
//	@NotNull(message = "Please provide a email")
	private String email;
	
	@Size(max = 100)
	private String address;
	
	@Size(max = 20)
	private String mobile;
	
	private String about;
	
	@Size(max = 100)
	private String facebook;
	
	private String code;
	
	@ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "user_role",
		joinColumns = { @JoinColumn(name = "id_user") },
		inverseJoinColumns = { @JoinColumn(name = "id_role")}
	)
	Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE
		})
	@JoinTable(name = "user_clinic",
		joinColumns = { @JoinColumn(name = "id_user") },
		inverseJoinColumns = { @JoinColumn(name = "id_clinic")}
	)
	Set<Clinic> clinics = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Post> posts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Faculty> faculties = new HashSet<>();
	
//	@OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "expert")
//    private Set<Comment> commentExperts = new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "expert_comment",
		joinColumns = { @JoinColumn(name = "id_expert") },
		inverseJoinColumns = { @JoinColumn(name = "id_commnet")}
	)
	private Set<Comment> commentExperts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Comment> commentUsers = new HashSet<>();
	
//	@OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "expert")
//    private Set<Booking> bookingExperts = new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "expert_booking",
		joinColumns = { @JoinColumn(name = "id_expert") },
		inverseJoinColumns = { @JoinColumn(name = "id_booking")}
	)
	private Set<Booking> bookingExperts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Booking> bookingUsers = new HashSet<>();
	
//	@OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "expert")
//    private Set<Rate> rateExperts = new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "expert_rate",
		joinColumns = { @JoinColumn(name = "id_expert") },
		inverseJoinColumns = { @JoinColumn(name = "id_rate")}
	)
	private Set<Rate> rateExperts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Rate> rateUsers = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Attachment> attachments = new HashSet<>();
	
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Degree> degrees = new HashSet<>();
	
	public User() {
		 
	}
	
	public User(String username, String password, String fullName,
			String email, String mobile) {
			this.username = username;
			this.password = password;
			this.fullName = fullName;
			this.email = email;
			this.mobile = mobile;
	}
	
	public User(String password, String code) {
		this.password = password;
		this.code = code;
	}

	public User(String id, String fullName, Date birthday, UserGender gender, Integer age,String address,
			String email, String mobile,String about, Set<Faculty> faculties, Set<Degree> degrees) {
		this.setId(id);
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.about = about;
		this.faculties = faculties;
		this.degrees = degrees;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getBadPoint() {
		return badPoint;
	}

	public void setBadPoint(Integer badPoint) {
		this.badPoint = badPoint;
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

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
