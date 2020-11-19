package com.microservice.user.importadora.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department", catalog = "importadora")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private String description;
	private List<User> users = new ArrayList<>();
	
	public Department() {
	}

	public Department(Integer departmentId, String description) {
		this.departmentId = departmentId;
		this.description = description;
	}

	@Id
	@Column(name = "department_id", unique = true)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
