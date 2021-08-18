package com.herms.hermscoder.service;

import com.cloudinary.utils.ObjectUtils;
import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.model.cloudinary.CloudinaryManager;
import com.herms.hermscoder.model.dto.MediaDTO;
import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private CloudinaryManager cloudinaryManager;

    public MediaDTO addMedia(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryManager.getCloudinaryInstance()
                .uploader()
                .upload(file.getBytes(), Collections.emptyMap());
        Media media = new Media("IMG", (String)uploadResult.get("url"), (String) uploadResult.get("public_id").toString());

        return new MediaDTO(mediaRepository.save(media));
    }

    public MediaDTO updateMediaContentOnCloud(Long mediaId, MultipartFile content){
        Media media = mediaRepository.getById(mediaId);
        try {
            byte[] bytes = content.getBytes();
            if (bytes.length > 0) {
                if (media.getPublicId() != null) {
                    Map deleteResult = cloudinaryManager.destroy(media.getPublicId(), ObjectUtils.emptyMap());
                }
                Map uploadResult = null;

                uploadResult = cloudinaryManager.getCloudinaryInstance().uploader().upload(bytes, ObjectUtils.emptyMap());


                media.setUrl((String) uploadResult.get("url"));
                media.setPublicId((String) uploadResult.get("public_id").toString());

                return new MediaDTO(mediaRepository.save(media));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new HermsCoderException(e.getMessage());
        }
        return new MediaDTO(media);
    }

    public MediaDTO findById(Long id){
        return new MediaDTO(mediaRepository.findById(id).get());
    }

    public void deleteMedia(Long id) throws IOException {
        Media toBeDeleted = mediaRepository.findById(id).get();
        deleteMedia(toBeDeleted);
    }
    public void deleteMedia(Media media) throws IOException {
        if(media.getPublicId() != null) {
            cloudinaryManager.getCloudinaryInstance().uploader().destroy(media.getPublicId(), ObjectUtils.emptyMap());
        }
        mediaRepository.delete(media.getId());
    }
}
