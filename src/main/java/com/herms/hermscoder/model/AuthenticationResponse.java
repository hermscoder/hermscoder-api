package com.herms.hermscoder.model;

import com.herms.hermscoder.model.dto.UserDTO;

public class AuthenticationResponse {
    private String authenticationToken;
    private UserDTO user;

    public AuthenticationResponse(String authenticationToken, UserDTO user) {
        this.authenticationToken = authenticationToken;
        this.user = user;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}