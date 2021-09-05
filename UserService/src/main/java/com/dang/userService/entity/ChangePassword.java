package com.dang.userService.entity;

import javax.validation.constraints.NotNull;

public class ChangePassword {
    @NotNull
    private String userId;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    public ChangePassword() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
