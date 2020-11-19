package com.microservice.user.importadora.util;

public class UserBuilder {

	private String user;
	private String password;
	private Integer departId;
	private Integer privId;
	private String userName;
	private String role;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Integer getPrivId() {
		return privId;
	}

	public void setPrivId(Integer privId) {
		this.privId = privId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public UserBuilder() {
		super();
	}

	public UserBuilder(Builder builder) {
		super();
		this.user = builder.user;
		this.password = builder.password;
		this.departId = builder.departId;
		this.privId = builder.privId;
		this.userName = builder.userName;
		this.role = builder.role;
	}

	public static class Builder {

		private String user;
		private String password;
		private Integer departId;
		private Integer privId;
		private String userName;
		private String role;

		public Builder addUser(String user) {
			this.user = user;
			return this;
		}

		public Builder addPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder addDepartId(Integer departId) {
			this.departId = departId;
			return this;
		}

		public Builder addPrivId(Integer privId) {
			this.privId = privId;
			return this;
		}

		public Builder addUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder addRole(String role) {
			this.role = role;
			return this;
		}
		
		public UserBuilder buildInsertNewUser() {
			return new UserBuilder(this);
		}

	}

}
