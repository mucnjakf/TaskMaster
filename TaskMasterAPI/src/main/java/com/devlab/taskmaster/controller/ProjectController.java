package com.devlab.taskmaster.controller;

import com.devlab.taskmaster.dto.project.CreateProjectDTO;
import com.devlab.taskmaster.dto.project.ProjectDTO;
import com.devlab.taskmaster.dto.project.UpdateProjectDTO;
import com.devlab.taskmaster.dto.task.TaskDTO;
import com.devlab.taskmaster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjects() {
        List<ProjectDTO> projectDTOs = projectService.getProjects();

        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Integer id) {
        ProjectDTO projectDTO = projectService.getProject(id);

        return ResponseEntity.ok(projectDTO);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO) {
        ProjectDTO projectDTO = projectService.createProject(createProjectDTO);

        return ResponseEntity.created(URI.create("/api/projects/" + projectDTO.getId())).body(projectDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @RequestBody UpdateProjectDTO updateProjectDTO) {
        projectService.updateProject(id, updateProjectDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }
}
