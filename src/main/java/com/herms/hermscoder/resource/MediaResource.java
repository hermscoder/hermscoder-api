package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.dto.MediaDTO;
import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaResource {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<MediaDTO> uploadFile(@Valid @RequestPart("file") MultipartFile file) throws IOException {
        MediaDTO media = mediaService.addMedia(file);
        return ResponseEntity.ok(media);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MediaDTO> changeMediaOnCloud(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id) {
        return ResponseEntity.ok(mediaService.updateMediaContentOnCloud(id, multipartFile));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable("id") Long id) throws IOException {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
