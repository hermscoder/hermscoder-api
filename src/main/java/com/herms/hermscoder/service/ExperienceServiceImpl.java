package com.herms.hermscoder.service;

import com.herms.hermscoder.model.dto.ExperienceDTO;
import com.herms.hermscoder.model.dto.ProjectDTO;
import com.herms.hermscoder.repository.ExperienceRepository;
import com.herms.hermscoder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements BlogService<ExperienceDTO> {

    @Autowired
    private ExperienceRepository projectRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    @Override
    public List<ExperienceDTO> findAll() {
        return projectRepository.findAll().stream().map(ExperienceDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<ExperienceDTO> findByProfileId(Long profileId) {
        return projectRepository.findByProfileId(profileId).stream().map(ExperienceDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ExperienceDTO findById(Long id) {
        var project = projectRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Experience for id " + id + " not found!"));

        return new ExperienceDTO(project);
    }

    @Transactional
    @Override
    public ExperienceDTO save(ExperienceDTO dto) {
        if(dto.getId() == null){
            var project = dto.toExperience();
            return new ExperienceDTO(projectRepository.save(project));
        } else {
            return update(dto);
        }
    }

    @Transactional
    @Override
    public ExperienceDTO update(ExperienceDTO dto) {
        var experience = dto.toExperience();
        if(dto.getId() == null){
            save(dto);
        } else {
            projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Experience with id " + dto.getId() + " not found."));
        }

        return new ExperienceDTO(projectRepository.save(experience));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }


}
