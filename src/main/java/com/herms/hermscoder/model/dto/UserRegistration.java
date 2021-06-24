package com.herms.hermscoder.model.dto;

import javax.validation.constraints.NotNull;

public class UserRegistration {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private ProfileDTO profile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
