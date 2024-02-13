package com.devlab.taskmaster.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private List<UserProjectDTO> projects;

    private List<UserTaskDTO> tasks;

    private String role;
}
