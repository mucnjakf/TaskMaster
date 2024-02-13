package com.devlab.taskmaster.service;

import com.devlab.taskmaster.dto.project.*;
import com.devlab.taskmaster.dto.task.TaskDTO;
import com.devlab.taskmaster.dto.task.TaskProjectDTO;
import com.devlab.taskmaster.dto.task.TaskUserDTO;
import com.devlab.taskmaster.entity.Project;
import com.devlab.taskmaster.entity.Task;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public List<ProjectDTO> getProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (Project project : projects) {
            projectDTOs.add(mapProjectToProjectDTO(project));
        }

        return projectDTOs;
    }

    public ProjectDTO getProject(Integer id) {
        Project project = getProjectEntity(id);

        return mapProjectToProjectDTO(project);
    }

    public ProjectDTO createProject(CreateProjectDTO createProjectDTO) {
        User assignee = userService.getUserEntity(createProjectDTO.getAssigneeId());

        Project project = Project
                .builder()
                .name(createProjectDTO.getName())
                .description(createProjectDTO.getDescription())
                .assignee(assignee)
                .build();

        projectRepository.save(project);

        return mapProjectToProjectDTO(project);
    }

    public void updateProject(Integer id, UpdateProjectDTO updateProjectDTO) {
        Project project = getProjectEntity(id);

        project.setName(updateProjectDTO.getName());
        project.setDescription(updateProjectDTO.getDescription());

        projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        Project project = getProjectEntity(id);

        if (project.getTasks() == null || !project.getTasks().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete project that contains tasks");
        }

        projectRepository.delete(project);
    }

    public Project getProjectEntity(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    private ProjectDTO mapProjectToProjectDTO(Project project) {
        List<ProjectTaskDTO> tasks = new ArrayList<>();

        if (project.getTasks() != null) {
            tasks = project
                    .getTasks()
                    .stream()
                    .map(x -> ProjectTaskDTO
                            .builder()
                            .id(x.getId())
                            .name(x.getName())
                            .status(x.getStatus())
                            .priority(x.getPriority())
                            .assignee(x.getAssignee().getFullName())
                            .build())
                    .toList();
        }

        return ProjectDTO
                .builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .tasks(tasks)
                .assignee(ProjectUserDTO
                        .builder()
                        .id(project.getAssignee().getId())
                        .email(project.getAssignee().getEmail())
                        .fullName(project.getAssignee().getFullName())
                        .build())
                .build();
    }
}
