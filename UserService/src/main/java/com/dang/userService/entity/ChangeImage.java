package com.dang.userService.entity;

import javax.validation.constraints.NotNull;

public class ChangeImage {
    @NotNull
    private String userId;

    @NotNull
    private String imgUrl;

    public ChangeImage() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
