package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.User;

public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getUsername() {
       return username;
    }

    public String getEmail() {
        return email;
    }
}
