package com.microservice.user.importadora.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privileges", catalog = "importadora")
public class Privileges implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer privilege_id;
	private String privilegeDescription;

	public Privileges() {
	}

	public Privileges(Integer privilege_id, String privilegeDescription) {
		this.privilege_id = privilege_id;
		this.privilegeDescription = privilegeDescription;
	}

	@Id
	@Column(name = "privilege_id", unique = true, nullable = false)
	public Integer getPrivilegeId() {
		return this.privilege_id;
	}

	public void setPrivilegeId(int privilege_id) {
		this.privilege_id = privilege_id;
	}

	@Column(name = "privilege_description", nullable = false, length = 200)
	public String getPrivilegeDescription() {
		return this.privilegeDescription;
	}

	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
}
