package com.devlab.taskmaster.serviceTests;

import com.devlab.taskmaster.data.ProjectMockData;
import com.devlab.taskmaster.data.UserMockData;
import com.devlab.taskmaster.dto.project.ProjectDTO;
import com.devlab.taskmaster.repository.ProjectRepository;
import com.devlab.taskmaster.service.ProjectService;
import com.devlab.taskmaster.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProjectService sut;

    @Test
    public void getProjects_should_return_projects() {
        when(projectRepository.findAll()).thenReturn(ProjectMockData.getProjects());

        List<ProjectDTO> projects = sut.getProjects();

        assertNotNull(projects);
        assertEquals(1, projects.size());
        verify(projectRepository).findAll();
    }

    @Test
    public void getProject_should_return_project() {
        when(projectRepository.findById(ProjectMockData.getProject(true).getId())).thenReturn(Optional.of(ProjectMockData.getProject(true)));

        ProjectDTO project = sut.getProject(ProjectMockData.getProject(true).getId());

        assertNotNull(project);
        assertEquals(ProjectMockData.getProject(true).getId(), project.getId());
        verify(projectRepository).findById(ProjectMockData.getProject(true).getId());
    }

    @Test
    public void createProject_should_create_and_return_project() {
        when(userService.getUserEntity(any())).thenReturn(UserMockData.getUser());

        ProjectDTO project = sut.createProject(ProjectMockData.getCreateProjectDTO());

        assertNotNull(project);
        assertEquals(ProjectMockData.getCreateProjectDTO().getName(), project.getName());
        verify(userService).getUserEntity(any());
        verify(projectRepository).save(any());
    }

    @Test
    public void updateProject_should_update() {
        when(projectRepository.findById(any())).thenReturn(Optional.of(ProjectMockData.getProject(true)));

        sut.updateProject(any(), ProjectMockData.getUpdateProjectDTO());

        verify(projectRepository).findById(any());
        verify(projectRepository).save(any());
    }

    @Test
    public void deleteProject_should_delete() {
        when(projectRepository.findById(any())).thenReturn(Optional.of(ProjectMockData.getProject(false)));

        sut.deleteProject(any());

        verify(projectRepository).findById(any());
        verify(projectRepository).delete(any());
    }

    @Test
    public void getProjectEntity_should_return_entity() {
        when(projectRepository.findById(ProjectMockData.getProject(true).getId())).thenReturn(Optional.of(ProjectMockData.getProject(false)));

        sut.getProjectEntity(ProjectMockData.getProject(true).getId());

        verify(projectRepository).findById(ProjectMockData.getProject(true).getId());
    }
}
