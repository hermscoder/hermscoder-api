package com.herms.hermscoder.model.dto;

public class SharePostContentDTO {
    private String authorizationCode;
    private String redirectUri;
    private String commentaryText;
    private String postUrl;

    public SharePostContentDTO() {
    }

    public SharePostContentDTO(String authorizationCode, String redirectUri, String commentaryText, String postUrl) {
        this.authorizationCode = authorizationCode;
        this.redirectUri = redirectUri;
        this.commentaryText = commentaryText;
        this.postUrl = postUrl;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCommentaryText() {
        return commentaryText;
    }

    public void setCommentaryText(String commentaryText) {
        this.commentaryText = commentaryText;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
