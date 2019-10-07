package com.foody.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
	value = {"createAt", "updateAt"},
	allowGetters = true
)
public abstract class AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private Date createAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", nullable = false)
	@LastModifiedDate
	private Date updateAt;

	private boolean isActive = true;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String updatedBy  ;
	
	private String deletedBy ;
	
	public AuditEntity() {
		super();
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
	
	public boolean equals(Object obj) {
        if (obj instanceof AuditEntity) {
        	AuditEntity another = (AuditEntity) obj;
            if (this.getId().equals(another.getId())) {
                return true;
            }
        }
        return false;
    }
}
