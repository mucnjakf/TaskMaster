package com.devlab.taskmaster.service;

import com.devlab.taskmaster.dto.task.*;
import com.devlab.taskmaster.entity.Project;
import com.devlab.taskmaster.entity.Task;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.enumeration.TaskStatus;
import com.devlab.taskmaster.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public List<TaskDTO> getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOs = new ArrayList<>();

        for (Task task : tasks) {
            taskDTOs.add(mapTaskToTaskDTO(task));
        }

        return taskDTOs;
    }

    public TaskDTO getTask(Integer id) {
        Task task = getTaskEntity(id);

        return mapTaskToTaskDTO(task);
    }

    public TaskCountDTO getTaskCount() {
        return TaskCountDTO
                .builder()
                .totalTasks((int) taskRepository.count())
                .tasksInBacklog(getTaskCountByStatus(TaskStatus.Backlog))
                .tasksInProgress(getTaskCountByStatus(TaskStatus.InProgress))
                .tasksCompleted(getTaskCountByStatus(TaskStatus.Completed))
                .build();
    }

    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        Project project = projectService.getProjectEntity(createTaskDTO.getProjectId());
        User assignee = userService.getUserEntity(createTaskDTO.getAssigneeId());

        Task task = Task
                .builder()
                .name(createTaskDTO.getName())
                .status(createTaskDTO.getStatus())
                .priority(createTaskDTO.getPriority())
                .project(project)
                .assignee(assignee)
                .build();

        taskRepository.save(task);

        return mapTaskToTaskDTO(task);
    }

    public void updateTask(Integer id, UpdateTaskDTO updateTaskDTO) {
        Task task = getTaskEntity(id);

        task.setName(updateTaskDTO.getName());
        task.setStatus(updateTaskDTO.getStatus());
        task.setPriority(updateTaskDTO.getPriority());

        taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        Task task = getTaskEntity(id);

        taskRepository.delete(task);
    }

    public Task getTaskEntity(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public TaskDTO mapTaskToTaskDTO(Task task) {
        return TaskDTO
                .builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .project(TaskProjectDTO
                        .builder()
                        .id(task.getProject().getId())
                        .name(task.getProject().getName())
                        .description(task.getProject().getDescription())
                        .assignee(task.getAssignee().getFullName())
                        .build()
                )
                .assignee(TaskUserDTO
                        .builder()
                        .id(task.getAssignee().getId())
                        .email(task.getAssignee().getEmail())
                        .fullName(task.getAssignee().getFullName())
                        .build()
                )
                .build();
    }

    private Integer getTaskCountByStatus(TaskStatus status) {
        return (int) StreamSupport
                .stream(taskRepository.findAll().spliterator(), false)
                .filter(x -> x.getStatus() == status)
                .count();
    }
}
