package com.herms.hermscoder.model.integration.pexels;

import java.util.List;

public class SearchPexelsResponse {
    private List<PhotoPexels> photos;

    public SearchPexelsResponse() {
    }

    public List<PhotoPexels> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoPexels> photos) {
        this.photos = photos;
    }
}
