package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.dto.ProjectDTO;
import com.herms.hermscoder.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectResource {
    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> get() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> post(@RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.save(dto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectDTO> put(@PathVariable("id") Long id, @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
