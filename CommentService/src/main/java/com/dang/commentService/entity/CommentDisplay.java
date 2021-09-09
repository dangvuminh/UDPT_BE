package com.dang.commentService.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CommentDisplay {

    private Integer commentId;

    private Integer forumId;

    private String userId;

    private Integer num_of_likes;

    private String commentContent;

    private String firstName;

    private String lastName;

    private String imgUrl;

    public CommentDisplay() {
    }

    public CommentDisplay(Integer commentId, Integer forumId, String userId, Integer num_of_likes, String commentContent, String firstName, String lastName, String imgUrl) {
        this.commentId = commentId;
        this.forumId = forumId;
        this.userId = userId;
        this.num_of_likes = num_of_likes;
        this.commentContent = commentContent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
