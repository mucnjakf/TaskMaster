package com.devlab.taskmaster.dto.project;

import com.devlab.taskmaster.enumeration.TaskPriority;
import com.devlab.taskmaster.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskDTO {

    private Integer id;

    private String name;

    private TaskStatus status;

    private TaskPriority priority;

    private String assignee;
}
