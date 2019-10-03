package com.foody.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification_type")
public class NotificationType extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_notification")
    private Notification notification;

	public NotificationType() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
