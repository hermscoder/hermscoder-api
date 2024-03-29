package com.herms.hermscoder.resource;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.exception.OperationNotAuthorizedException;
import com.herms.hermscoder.model.dto.ExperienceDTO;
import com.herms.hermscoder.model.dto.ProfileDTO;
import com.herms.hermscoder.model.dto.ProjectDTO;
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
    private static final int MAIN_PROFILE_IDENTIFIER = 0;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> get() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping(path = "/{profile_id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable(value = "profile_id") long id) {
        ProfileDTO profileDTO;
        if(id == MAIN_PROFILE_IDENTIFIER) {
            profileDTO = profileService.findByMainProfile();
        } else {
            profileDTO = profileService.findById(id);
        }

        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping(path = "/{profile_id}")
    public ResponseEntity<ProfileDTO> put(@PathVariable("profile_id") Long id, @RequestBody ProfileDTO dto) {

        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(dto.getUser() == null  || !currentUser.getId().equals(dto.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });

        return ResponseEntity.ok(profileService.update(dto));
    }
    @DeleteMapping(path = "/{profile_id}")
    public ResponseEntity<Void> delete(@PathVariable("profile_id") Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{profile_id}/project")
    public ResponseEntity<ProjectDTO> post(@PathVariable("profile_id") Long profileId,
                                           @RequestBody @Valid ProjectDTO dto) {
        ProfileDTO profile = profileService.findById(dto.getProfileId());
        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(profile.getUser() == null  || !currentUser.getId().equals(profile.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });

        return ResponseEntity.ok(profileService.addProject(dto));
    }

    @PutMapping(path = "/{profile_id}/project/{project_id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("profile_id") Long profileId,
                                          @PathVariable("project_id") Long projectId,
                                          @RequestBody @Valid ProjectDTO dto) {

        ProfileDTO profile = profileService.findById(dto.getProfileId());
        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(profile.getUser() == null  || !currentUser.getId().equals(profile.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });
        return ResponseEntity.ok(profileService.updateProject(dto));
    }

    @PostMapping(path = "/{profile_id}/experience")
    public ResponseEntity<ExperienceDTO> addExperience(@PathVariable("profile_id") Long id,
                                                       @RequestBody @Valid ExperienceDTO dto) {
        ProfileDTO profile = profileService.findById(dto.getProfileId());
        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(profile.getUser() == null  || !currentUser.getId().equals(profile.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });

        return ResponseEntity.ok(profileService.addExperience(dto));
    }

    @PutMapping(path = "/{profile_id}/experience/{experience_id}")
    public ResponseEntity<ExperienceDTO> updateExperience(@PathVariable("profile_id") Long profileId,
                                                          @PathVariable("experience_id") Long experienceId,
                                                          @RequestBody @Valid ExperienceDTO dto) {
        ProfileDTO profile = profileService.findById(dto.getProfileId());
        authService.getCurrentUser().ifPresent((currentUser) -> {
            if(profile.getUser() == null  || !currentUser.getId().equals(profile.getUser().getId())){
                throw new HermsCoderException("Operation not allowed");
            }
        });

        return ResponseEntity.ok(profileService.updateExperience(dto));
    }

}
