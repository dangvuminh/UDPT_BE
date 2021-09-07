package com.dang.forumService.entity;

import javax.validation.constraints.NotNull;

public class LegalizeForum {
    @NotNull
    private Integer forumId;

    @NotNull
    private String userId;

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
}
