package com.devlab.taskmaster.data;

import com.devlab.taskmaster.dto.project.CreateProjectDTO;
import com.devlab.taskmaster.dto.project.UpdateProjectDTO;
import com.devlab.taskmaster.entity.Project;
import com.devlab.taskmaster.entity.Task;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.enumeration.Role;
import com.devlab.taskmaster.enumeration.TaskPriority;
import com.devlab.taskmaster.enumeration.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class ProjectMockData {

    public static Project getProject(Boolean includeTasks) {
        List<Task> tasks = new ArrayList<>();

        if (includeTasks) {
            tasks = List.of(Task
                    .builder()
                    .id(1)
                    .name("task 1")
                    .status(TaskStatus.Backlog)
                    .priority(TaskPriority.Low)
                    .assignee(User
                            .builder()
                            .id(1)
                            .email("person@mail.com")
                            .firstName("per")
                            .lastName("son")
                            .role(Role.USER)
                            .build()
                    )
                    .build()
            );
        }

        return Project
                .builder()
                .id(1)
                .name("project 1")
                .description("description 1")
                .tasks(tasks)
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


    public static List<Project> getProjects() {
        return List.of(getProject(true));
    }

    public static CreateProjectDTO getCreateProjectDTO() {
        return CreateProjectDTO
                .builder()
                .name("project 1")
                .description("description 1")
                .assigneeId(UserMockData.getUser().getId())
                .build();
    }

    public static UpdateProjectDTO getUpdateProjectDTO() {
        return UpdateProjectDTO
                .builder()
                .name("project 1")
                .description("description 1")
                .build();
    }
}
