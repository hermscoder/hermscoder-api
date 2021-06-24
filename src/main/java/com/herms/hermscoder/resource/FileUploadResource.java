package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.AuthenticationResponse;
import com.herms.hermscoder.model.dto.AuthenticationRequest;
import com.herms.hermscoder.model.dto.UploadResponse;
import com.herms.hermscoder.model.dto.UserRegistration;
import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.service.AuthService;
import com.herms.hermscoder.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadResource {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<UploadResponse> signUp(@Valid @RequestPart("file") MultipartFile file) throws IOException {
        Media media = mediaService.addMedia(file);
        return ResponseEntity.ok(new UploadResponse(media.getUrl()));
    }

}
