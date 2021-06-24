package com.herms.hermscoder.service;

import com.herms.hermscoder.model.cloudinary.CloudinaryManager;
import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private CloudinaryManager cloudinaryManager;

    public Media addMedia(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryManager.getCloudinaryInstance()
                .uploader()
                .upload(file.getBytes(), Collections.emptyMap());
        Media media = new Media("IMG", (String)uploadResult.get("url"), (String) uploadResult.get("public_id").toString());

        return mediaRepository.save(media);
    }
}
