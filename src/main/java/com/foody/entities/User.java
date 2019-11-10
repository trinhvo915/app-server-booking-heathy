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

import org.hibernate.annotations.Type;

import com.foody.entities.enums.UserGender;

@Entity
@Table(name = "user")
public class User extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	@Column(name = "fullname")
	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	@Column(name="bad_point")
	private Integer badPoint;
	
	private String email;
	
	@Size(max = 100)
	private String address;
	
	@Size(max = 20)
	private String mobile;
	
	@Type(type="text")
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
        },
        mappedBy = "users"
    )
	Set<Clinic> clinics = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Post> posts = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE
		})
	@JoinTable(name = "user_faculty",
		joinColumns = { @JoinColumn(name = "id_user") },
		inverseJoinColumns = { @JoinColumn(name = "id_faculty")}
	)
    private Set<Faculty> faculties = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "expert")
    private Set<Comment> commentExperts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Comment> commentUsers = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "expert")
	private Set<Booking> bookingExperts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Booking> bookingUsers = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "expert")
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
	
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE
		})
	@JoinTable(name = "user_degree",
		joinColumns = { @JoinColumn(name = "id_user") },
		inverseJoinColumns = { @JoinColumn(name = "id_degree")}
	)
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

	public User(String username, String fullName, Date birthday, UserGender gender, Integer age) {
		this.username = username;
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.age = age;
	}

	public User(String password, String code) {
		this.password = password;
		this.code = code;
	}

	public User(String fullName, Date birthday, UserGender gender, Integer age,String address,
			String email, String mobile,String about, Set<Faculty> faculties, Set<Degree> degrees) {
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

	public void setCommentExperts(Set<Comment> commentExperts) {
		this.commentExperts = commentExperts;
	}

	public void setBookingExperts(Set<Booking> bookingExperts) {
		this.bookingExperts = bookingExperts;
	}

	public void setRateExperts(Set<Rate> rateExperts) {
		this.rateExperts = rateExperts;
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
	
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
