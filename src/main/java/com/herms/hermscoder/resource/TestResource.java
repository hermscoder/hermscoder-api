package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class TestResource {
    @Autowired
    private PostServiceImpl postService;
    @PostMapping
    public ResponseEntity<PostDTO> post(@RequestBody PostDTO dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

}
