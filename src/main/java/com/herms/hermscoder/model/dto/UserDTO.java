package com.herms.hermscoder.model.dto;

import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import com.herms.hermscoder.utils.Utils;

public class UserDTO {
    private Long id;
    private String name;
    private String jobTitle;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        Profile profile = user.getProfile();
        if(profile != null) {
            this.name = Utils.joinStrings(profile.getGivenName(), " ", profile.getFamilyName()).trim();
            this.jobTitle = profile.getJobTitle();
        }
    }

    public User toUser(){
        var user = new User();
        user.setId(this.id);

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
