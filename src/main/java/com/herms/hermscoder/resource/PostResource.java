package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.model.dto.SharePostContentDTO;
import com.herms.hermscoder.model.integration.linkedin.PostShareResponse;
import com.herms.hermscoder.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostResource {
    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> get() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> post(@RequestBody PostDTO dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDTO> put(@PathVariable("id") Long id, @RequestBody PostDTO dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/share/linkedin")
    public ResponseEntity<PostShareResponse> sharePostOnLinkedin(@PathVariable("id") Long postId, @RequestBody SharePostContentDTO sharePostContent) {
        return ResponseEntity.ok(postService.sharePostOnLinkedin(postId, sharePostContent));
    }
}
