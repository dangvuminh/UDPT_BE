package com.dang.commentService.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;

@Table(name="comments")
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer commentId;

	@NotNull
	@Column(name = "forum_id_fk")
	private Integer forumId;
	
	@NotNull
	@Column(name = "user_id_fk")
	private String userId;
	
	@Column(name = "num_of_likes")
	private Integer num_of_likes;

	@NotNull
	@Column(name = "comment_content")
	private String commentContent;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getNum_of_likes() {
		return num_of_likes;
	}

	public void setNum_of_likes(Integer num_of_likes) {
		this.num_of_likes = num_of_likes;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}
