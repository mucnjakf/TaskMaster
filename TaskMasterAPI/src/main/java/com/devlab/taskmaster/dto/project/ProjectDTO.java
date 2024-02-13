package com.devlab.taskmaster.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer id;

    private String name;

    private String description;

    private List<ProjectTaskDTO> tasks;

    private ProjectUserDTO assignee;
}
