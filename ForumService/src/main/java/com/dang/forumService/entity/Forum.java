package com.dang.forumService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Table
@Entity
public class Forum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "forum_id")
	private Integer forum_id;
	
	@NotNull
	@Column(name = "forum_name")
	private String forum_name;
	
	@NotNull
	@Column(name = "forum_content")
	private String forum_content;
	
	@NotNull
	@Column(name = "user_id_fk")
	private String user_id_fk;
	
	@Column(name = "num_of_likes")
	private Integer num_of_likes;
	
	@Column(name = "is_legal")
	private boolean is_legal;
	
	@Column(name = "num_of_comments")
	private Integer num_of_comments;
	
	@Column(name = "tag")
	private String tag;
	
	@NotNull
	@Column(name = "category_id_fk")
	private Integer category_id_fk;
	
	
	public Integer getCategory_id_fk() {
		return category_id_fk;
	}
	
	public String getForum_content() {
		return forum_content;
	}
	
	public Integer getForum_id() {
		return forum_id;
	}
	
	public String getForum_name() {
		return forum_name;
	}
	
	public Integer getNum_of_comments() {
		return num_of_comments;
	}
	
	public Integer getNum_of_likes() {
		return num_of_likes;
	}
	
	public String getTag() {
		return tag;
	}
	
	
	public boolean isIs_legal() {
		return is_legal;
	}
	
	public void setCategory_id_fk(Integer category_id_fk) {
		this.category_id_fk = category_id_fk;
	}
	
	public void setForum_content(String forum_content) {
		this.forum_content = forum_content;
	}
	
	public void setForum_id(Integer forum_id) {
		this.forum_id = forum_id;
	}
	
	public void setForum_name(String forum_name) {
		this.forum_name = forum_name;
	}
	
	
	public void setIs_legal(boolean is_legal) {
		this.is_legal = is_legal;
	}
	
	public void setNum_of_comments(Integer num_of_comments) {
		this.num_of_comments = num_of_comments;
	}
	
	public void setNum_of_likes(Integer num_of_likes) {
		this.num_of_likes = num_of_likes;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getUser_id_fk() {
		return user_id_fk;
	}
	
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
}
