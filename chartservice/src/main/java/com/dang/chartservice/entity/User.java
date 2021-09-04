package com.dang.chartservice.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {

	private String user_id;

	private String username;

	private String password;

	private String first_name;

	private String last_name;

	private String email;

	private String profile_img;

	private boolean is_admin;

	private Integer numOfComments;

	public User() {

	}
	public User(String user_id, String username, String password, String first_name, String last_name, String email, String profile_img, boolean is_admin, Integer numOfComments) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.profile_img = profile_img;
		this.is_admin = is_admin;
		this.numOfComments = numOfComments;
	}

	public String getEmail() {
		return email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getPassword() {
		return password;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getUsername() {
		return username;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getNumOfComments() {
		return numOfComments;
	}

	public void setNumOfComments(Integer numOfComments) {
		this.numOfComments = numOfComments;
	}
}
