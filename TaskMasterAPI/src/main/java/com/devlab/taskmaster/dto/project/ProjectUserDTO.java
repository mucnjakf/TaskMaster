package com.devlab.taskmaster.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserDTO {

    private Integer id;

    private String email;

    private String fullName;
}
