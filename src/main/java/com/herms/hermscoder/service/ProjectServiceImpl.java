package com.herms.hermscoder.service;

import com.herms.hermscoder.model.dto.ProjectDTO;
import com.herms.hermscoder.model.entity.Project;
import com.herms.hermscoder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements BlogService<ProjectDTO> {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<ProjectDTO> findByProfileId(Long profileId) {
        return projectRepository.findByProfileId(profileId).stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProjectDTO findById(Long id) {
        var project = projectRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Project for id " + id + " not found!"));

        return new ProjectDTO(project);
    }

    @Transactional
    @Override
    public ProjectDTO save(ProjectDTO dto) {
        if(dto.getId() == null){
            var project = dto.toProject();
            return new ProjectDTO(projectRepository.save(project));
        } else {
            return update(dto);
        }
    }

    @Transactional
    @Override
    public ProjectDTO update(ProjectDTO dto) {
        var project = dto.toProject();
        if(dto.getId() == null){
            save(dto);
        } else {
            projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Project with id " + dto.getId() + " not found."));
        }

        return new ProjectDTO(projectRepository.save(project));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }


}
