package com.dang.forumService.entity;

public class ForumResponse {
	private String title;
	private String message;
	private Integer statusCode;
	
	public ForumResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ForumResponse(String title,String message, Integer statusCode ) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
