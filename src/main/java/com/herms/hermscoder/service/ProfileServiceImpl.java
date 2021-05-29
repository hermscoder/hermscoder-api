package com.herms.hermscoder.service;

import com.herms.hermscoder.model.Profile;
import com.herms.hermscoder.model.ProfileDTO;
import com.herms.hermscoder.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements BlogService<ProfileDTO> {

    @Autowired
    private ProfileRepository profileRepository;

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
        ProfileDTO dto = null;
        Profile entity = profileRepository.findById(id).orElse(null);
        if(entity != null) {
            dto = new ProfileDTO(entity);
        }
        return dto;
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
}
