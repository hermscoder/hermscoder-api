package com.herms.hermscoder.model.integration.linkedin;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkedInPostInformationDTO {

    @JsonProperty("author")
    private String authorId;
    private String lifecycleState = "PUBLISHED";
    private SpecificContent specificContent;
    private Visibility visibility;

    public LinkedInPostInformationDTO() {
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    public SpecificContent getSpecificContent() {
        return specificContent;
    }

    public void setSpecificContent(SpecificContent specificContent) {
        this.specificContent = specificContent;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
