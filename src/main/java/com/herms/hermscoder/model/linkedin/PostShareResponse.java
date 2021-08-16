package com.herms.hermscoder.model.linkedin;

public class PostShareResponse {
    private String postUrl;

    public PostShareResponse() {
    }

    public PostShareResponse(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
