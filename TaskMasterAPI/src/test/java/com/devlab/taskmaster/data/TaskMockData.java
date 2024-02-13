package com.devlab.taskmaster.data;

import com.devlab.taskmaster.dto.task.CreateTaskDTO;
import com.devlab.taskmaster.dto.task.TaskCountDTO;
import com.devlab.taskmaster.dto.task.UpdateTaskDTO;
import com.devlab.taskmaster.entity.Project;
import com.devlab.taskmaster.entity.Task;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.enumeration.Role;
import com.devlab.taskmaster.enumeration.TaskPriority;
import com.devlab.taskmaster.enumeration.TaskStatus;

import java.util.List;

public class TaskMockData {

    public static Task getTask() {
        return Task
                .builder()
                .id(1)
                .name("task 1")
                .status(TaskStatus.Backlog)
                .priority(TaskPriority.Low)
                .project(Project
                        .builder()
                        .id(1)
                        .name("project 1")
                        .description("description 1")
                        .build()
                )
                .assignee(User
                        .builder()
                        .id(1)
                        .email("person@mail.com")
                        .firstName("per")
                        .lastName("son")
                        .role(Role.USER)
                        .build()
                )
                .build();
    }

    public static List<Task> getTasks() {
        return List.of(getTask());
    }

    public static CreateTaskDTO getCreateTaskDTO() {
        return CreateTaskDTO
                .builder()
                .name("task 1")
                .status(TaskStatus.Backlog)
                .priority(TaskPriority.Low)
                .projectId(ProjectMockData.getProject(false).getId())
                .assigneeId(UserMockData.getUser().getId())
                .build();
    }

    public static UpdateTaskDTO getUpdateTaskDTO() {
        return UpdateTaskDTO
                .builder()
                .name("task 1")
                .status(TaskStatus.InProgress)
                .priority(TaskPriority.High)
                .build();
    }

    public static TaskCountDTO getTaskCountDTO() {
        return TaskCountDTO
                .builder()
                .totalTasks(5)
                .tasksInBacklog(3)
                .tasksInProgress(1)
                .tasksCompleted(1)
                .build();
    }

}
