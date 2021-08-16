package com.herms.hermscoder.model.linkedin;

import java.util.List;

public class LinkedinMedia {
    private String status = "READY";
    private String originalUrl;
    private List<Thumbnails> thumbnails;
    private Title title;

    public LinkedinMedia() {
    }

    public LinkedinMedia(String originalUrl, List<Thumbnails> thumbnails, Title title) {
        this.originalUrl = originalUrl;
        this.thumbnails = thumbnails;
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public List<Thumbnails> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnails> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
