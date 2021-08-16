package com.herms.hermscoder.model.linkedin;

import java.util.List;

public class ShareContent {
        private ShareCommentary shareCommentary;
        private String shareMediaCategory= "ARTICLE";
        private List<LinkedinMedia> media;

    public ShareContent() {
    }

    public ShareContent(ShareCommentary shareCommentary, List<LinkedinMedia> media) {
        this.shareCommentary = shareCommentary;
        this.media = media;
    }

    public ShareCommentary getShareCommentary() {
        return shareCommentary;
    }

    public void setShareCommentary(ShareCommentary shareCommentary) {
        this.shareCommentary = shareCommentary;
    }

    public String getShareMediaCategory() {
        return shareMediaCategory;
    }

    public void setShareMediaCategory(String shareMediaCategory) {
        this.shareMediaCategory = shareMediaCategory;
    }

    public List<LinkedinMedia> getMedia() {
        return media;
    }

    public void setMedia(List<LinkedinMedia> media) {
        this.media = media;
    }
}
