package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.ProfileDTO;
import com.herms.hermscoder.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileResource {
    @Autowired
    private ProfileServiceImpl profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> get() {
        return ResponseEntity.ok(profileService.findAll());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> post(@RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.save(dto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfileDTO> put(@PathVariable("id") Long id, @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.save(dto));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
