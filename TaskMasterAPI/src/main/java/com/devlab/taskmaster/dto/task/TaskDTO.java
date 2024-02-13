package com.devlab.taskmaster.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Integer id;

    private String name;

    private String status;

    private String priority;

    private TaskProjectDTO project;

    private TaskUserDTO assignee;
}
