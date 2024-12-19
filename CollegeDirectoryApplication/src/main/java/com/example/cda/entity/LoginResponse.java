package com.example.cda.entity;



public class LoginResponse {
    private User user;
    private Object profile;

    public LoginResponse(User user2, Object profile) {
        this.user = user2;
        this.profile = profile;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getProfile() {
        return profile;
    }

    public void setProfile(Object profile) {
        this.profile = profile;
    }
}
