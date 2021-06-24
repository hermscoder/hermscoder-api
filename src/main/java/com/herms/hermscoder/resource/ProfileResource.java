package com.herms.hermscoder.resource;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.exception.OperationNotAuthorizedException;
import com.herms.hermscoder.model.dto.ProfileDTO;
import com.herms.hermscoder.service.AuthService;
import com.herms.hermscoder.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileResource {
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> get() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfileDTO> put(@PathVariable("id") Long id, @RequestBody ProfileDTO dto) {

        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(dto.getUser() == null  || !currentUser.getId().equals(dto.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });

        return ResponseEntity.ok(profileService.update(dto));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
