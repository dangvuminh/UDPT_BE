package com.dang.commentService.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;

@Table(name="comments")
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer comment_id;

	@NotNull
	@Column(name = "forum_id_fk")
	private Integer forumId;
	
	@NotNull
	@Column(name = "user_id_fk")
	private String user_id_fk;
	
	@Column(name = "num_of_likes")
	private Integer num_of_likes;
	
	@Column(name = "comment_content")
	private String comment_content;
	
	public String getComment_content() {
		return comment_content;
	}
	
	public Integer getComment_id() {
		return comment_id;
	}
	
	public Integer getForum_id_fk() {
		return forumId;
	}
	
	public Integer getNum_of_likes() {
		return num_of_likes;
	}
	
	public String getUser_id_fk() {
		return user_id_fk;
	}
	
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	
	public void setForum_id_fk(Integer forumId) {
		this.forumId = forumId;
	}
	
	public void setNum_of_likes(Integer num_of_likes) {
		this.num_of_likes = num_of_likes;
	}
	
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	
	
}
