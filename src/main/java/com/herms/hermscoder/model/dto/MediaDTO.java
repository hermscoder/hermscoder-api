package com.herms.hermscoder.model.dto;

import com.herms.hermscoder.model.entity.Media;

public class MediaDTO {

    private Long id;
    private String type;
    private String url;

    public MediaDTO() {
    }

    public MediaDTO(Media media) {
        this.id = media.getId();
        this.type = media.getType();
        this.url = media.getUrl();
    }

    public Media toMedia(){
        Media media = new Media();
        media.setId(this.id);
        media.setType(this.type);
        media.setUrl(this.url);

        return media;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
