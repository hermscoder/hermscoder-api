package com.herms.hermscoder.model.integration.linkedin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecificContent {
    @JsonProperty("com.linkedin.ugc.ShareContent")
    private ShareContent shareContent;

    public SpecificContent() {
    }

    public SpecificContent(ShareContent shareContent) {
        this.shareContent = shareContent;
    }

    public ShareContent getShareContent() {
        return shareContent;
    }

    public void setShareContent(ShareContent shareContent) {
        this.shareContent = shareContent;
    }
}

