package com.herms.hermscoder.model.integration.linkedin;

public class ShareCommentary {
    private String text;

    public ShareCommentary() {
    }

    public ShareCommentary(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
