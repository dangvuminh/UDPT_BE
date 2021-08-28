package com.dang.userService.entity;


public class UserResponse {
	private String title;
	private String message;
	private String statusCode;
	
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	public UserResponse(String title,String message, String statusCode ) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
