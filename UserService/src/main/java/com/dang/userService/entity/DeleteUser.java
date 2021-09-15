package com.dang.userService.entity;

import javax.validation.constraints.NotNull;

public class DeleteUser {
    @NotNull
    private String myUserId;

    @NotNull
    private String userId;

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
