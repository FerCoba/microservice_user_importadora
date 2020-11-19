package com.microservice.user.importadora.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "user", catalog = "importadora")
@JsonInclude(Include.NON_NULL)
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_DEFAULT)
	private Integer userId;
	private Department department;
	private Privileges privileges;
	private String user;
	private String password;
	private String userName;
	private String role;
	@JsonInclude(Include.NON_DEFAULT)
	private int departId;
	private int privId;

	public User() {
	}

	public User(Integer userId, Department department, Privileges privileges, String user, String password,
			String userName, String role) {
		super();
		this.userId = userId;
		this.department = department;
		this.privileges = privileges;
		this.user = user;
		this.password = password;
		this.userName = userName;
		this.role = role;
	}

	public User(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}

	public int getPrivId() {
		return privId;
	}

	public void setPrivId(int privId) {
		this.privId = privId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}
	
	public int getDepartId() {
		return departId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(targetEntity = Department.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "departmentId", nullable = false)
	@JsonBackReference(value = "department")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "privilege_id", nullable = false)
	@JsonBackReference(value = "privileges")
	public Privileges getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Privileges privileges) {
		this.privileges = privileges;
	}

	@Column(name = "user", nullable = false, length = 45)
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_name", length = 200)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "role", length = 200)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", department=" + department + ", privileges=" + privileges + ", user=" + user
				+ ", password=" + password + ", userName=" + userName + "]";
	}
}
