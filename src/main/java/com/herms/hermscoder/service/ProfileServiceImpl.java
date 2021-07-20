package com.herms.hermscoder.service;

import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.dto.ProfileDTO;
import com.herms.hermscoder.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements BlogService<ProfileDTO> {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ExperienceServiceImpl experienceService;

    @Value("${main.profile.user.email}")
    private String mainProfileUserEmail;

    public ProfileDTO findOrCreateProfile(){
        List<ProfileDTO> profileList = findAll();

        if(!profileList.isEmpty()) {
            return profileList.get(0);
        } else {
            var profile = new Profile();
            profile.setGivenName("Given Name");
            profile.setFamilyName("Family Name");
            profile.setBirthDate(LocalDate.now());
            return save(new ProfileDTO(profile));
        }
    }
    @Override
    public List<ProfileDTO> findAll() {
        return profileRepository.findAll().stream().map(ProfileDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProfileDTO findById(Long id) {
        Profile entity = profileRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Profile for id " + id + " not found!"));
        return new ProfileDTO(entity);
    }

    @Override
    public ProfileDTO save(ProfileDTO dto) {
        dto.setActive(true);
        return new ProfileDTO(profileRepository.save(dto.toProfile()));
    }

    @Override
    public ProfileDTO update(ProfileDTO dto) {
        return new ProfileDTO(profileRepository.save(dto.toProfile()));
    }

    @Override
    public void delete(Long id) {
        ProfileDTO dto = findById(id);
        dto.setActive(false);
        update(dto);
    }

    @Transactional
    protected Profile findByUserId(Long id) {
        return profileRepository.findByUserId(id);
    }

    @Transactional
    public ProfileDTO findByMainProfile() {
        Profile entity = profileRepository.findByUserEmail(mainProfileUserEmail).orElseThrow(() ->
                new EntityNotFoundException("Profile information not found!"));

        ProfileDTO dto = new ProfileDTO(entity);

        dto.setProjectsList(projectService.findByProfileId(entity.getId()));
        dto.setExperienceList(experienceService.findByProfileId(entity.getId()));

        return dto;
    }
}
