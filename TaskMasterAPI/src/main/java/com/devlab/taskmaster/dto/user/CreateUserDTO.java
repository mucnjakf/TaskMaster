package com.devlab.taskmaster.dto.user;

import com.devlab.taskmaster.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;
}
