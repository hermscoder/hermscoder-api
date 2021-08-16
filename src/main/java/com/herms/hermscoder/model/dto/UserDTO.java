package com.herms.hermscoder.model.dto;

import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import com.herms.hermscoder.utils.Utils;

public class UserDTO {
    private Long id;
    private String name;
    //Profile related fields
    private String jobTitle;
    private String linkedIn;
    private String instagram;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        Profile profile = user.getProfile();
        if(profile != null) {
            this.name = Utils.joinStrings(profile.getGivenName(), " ", profile.getFamilyName()).trim();
            this.jobTitle = profile.getJobTitle();
            this.linkedIn = profile.getLinkedIn();
            this.instagram = profile.getInstagram();
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

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
