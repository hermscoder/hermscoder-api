package com.herms.hermscoder.model.integration.pexels;

public class PhotoPexels {
    private Long id;
    private String url;
    private PhotoSource src;

    public PhotoPexels() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PhotoSource getSrc() {
        return src;
    }

    public void setSrc(PhotoSource src) {
        this.src = src;
    }
}
