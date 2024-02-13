package com.devlab.taskmaster.serviceTests;

import com.devlab.taskmaster.data.ProjectMockData;
import com.devlab.taskmaster.data.TaskMockData;
import com.devlab.taskmaster.data.UserMockData;
import com.devlab.taskmaster.dto.project.ProjectDTO;
import com.devlab.taskmaster.dto.task.TaskDTO;
import com.devlab.taskmaster.repository.TaskRepository;
import com.devlab.taskmaster.service.ProjectService;
import com.devlab.taskmaster.service.TaskService;
import com.devlab.taskmaster.service.UserService;
import org.junit.Assert;
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
public class TaskServiceTests {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private TaskService sut;

    @Test
    public void getTasks_should_return_tasks() {
        when(taskRepository.findAll()).thenReturn(TaskMockData.getTasks());

        List<TaskDTO> tasks = sut.getTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verify(taskRepository).findAll();
    }

    @Test
    public void getTask_should_return_task() {
        when(taskRepository.findById(TaskMockData.getTask().getId())).thenReturn(Optional.of(TaskMockData.getTask()));

        TaskDTO task = sut.getTask(TaskMockData.getTask().getId());

        assertNotNull(task);
        assertEquals(TaskMockData.getTask().getId(), task.getId());
        verify(taskRepository).findById(TaskMockData.getTask().getId());
    }

    @Test
    public void createTask_should_create_and_return_task() {
        when(projectService.getProjectEntity(any())).thenReturn(ProjectMockData.getProject(false));
        when(userService.getUserEntity(any())).thenReturn(UserMockData.getUser());

        TaskDTO task = sut.createTask(TaskMockData.getCreateTaskDTO());

        assertNotNull(task);
        assertEquals(TaskMockData.getCreateTaskDTO().getName(), task.getName());
        verify(projectService).getProjectEntity(any());
        verify(userService).getUserEntity(any());
        verify(taskRepository).save(any());
    }

    @Test
    public void updateTask_should_update() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(TaskMockData.getTask()));

        sut.updateTask(any(), TaskMockData.getUpdateTaskDTO());

        verify(taskRepository).findById(any());
        verify(taskRepository).save(any());
    }

    @Test
    public void deleteTask_should_delete() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(TaskMockData.getTask()));

        sut.deleteTask(any());

        verify(taskRepository).findById(any());
        verify(taskRepository).delete(any());
    }

    @Test
    public void getTaskEntity_should_return_entity() {
        when(taskRepository.findById(TaskMockData.getTask().getId())).thenReturn(Optional.of(TaskMockData.getTask()));

        sut.getTaskEntity(TaskMockData.getTask().getId());

        verify(taskRepository).findById(TaskMockData.getTask().getId());
    }
}
