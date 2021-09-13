package com.dang.userService.entity;

import javax.validation.constraints.NotNull;

public class UserSignIn {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserSignIn() {
    }

    public UserSignIn(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
