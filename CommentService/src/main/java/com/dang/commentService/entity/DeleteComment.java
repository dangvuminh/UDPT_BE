package com.dang.commentService.entity;

import javax.validation.constraints.NotNull;

public class DeleteComment {
    @NotNull
    private Integer forumId;

    @NotNull
    private String userId;

    @NotNull
    private Integer commentId;

    public DeleteComment() {
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
