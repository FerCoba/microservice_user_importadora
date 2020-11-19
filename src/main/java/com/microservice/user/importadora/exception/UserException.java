package com.microservice.user.importadora.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public UserException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public UserException() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserException(String message) {
		super();
		this.message = message;
	}

}
