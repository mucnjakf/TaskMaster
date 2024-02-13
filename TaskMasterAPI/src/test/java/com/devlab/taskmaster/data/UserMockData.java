package com.devlab.taskmaster.data;

import com.devlab.taskmaster.dto.user.CreateUserDTO;
import com.devlab.taskmaster.dto.user.LoginDTO;
import com.devlab.taskmaster.dto.user.UpdateUserDTO;
import com.devlab.taskmaster.entity.Project;
import com.devlab.taskmaster.entity.Task;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.enumeration.Role;
import com.devlab.taskmaster.enumeration.TaskPriority;
import com.devlab.taskmaster.enumeration.TaskStatus;

import java.util.List;

public class UserMockData {

    public static User getUser() {
        return User
                .builder()
                .id(1)
                .email("test@mail.com")
                .password("password")
                .firstName("first name 1")
                .lastName("last name 1")
                .role(Role.USER)
                .projects(
                        List.of(Project
                                .builder()
                                .id(1)
                                .name("project 1")
                                .description("description 1")
                                .build()
                        )
                )
                .tasks(
                        List.of(Task
                                .builder()
                                .id(1)
                                .name("task 1")
                                .status(TaskStatus.Backlog)
                                .priority(TaskPriority.Low)
                                .build()
                        )
                )
                .build();
    }

    public static List<User> getUsers() {
        return List.of(getUser());
    }

    public static LoginDTO getLoginDTO() {
        return LoginDTO
                .builder()
                .email("email@mail.com")
                .password("asdQWE123.")
                .build();
    }

    public static CreateUserDTO getCreateUserDTO() {
        return CreateUserDTO
                .builder()
                .email("email@mail.com")
                .password("asdQWE123.")
                .firstName("first name 1")
                .lastName("last name 1")
                .role(Role.USER)
                .build();
    }

    public static UpdateUserDTO getUpdateUserDTO() {
        return UpdateUserDTO
                .builder()
                .firstName("first name 1")
                .lastName("last name 1")
                .build();
    }
}
