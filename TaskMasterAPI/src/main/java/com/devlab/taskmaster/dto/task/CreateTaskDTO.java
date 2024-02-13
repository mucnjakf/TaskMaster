package com.devlab.taskmaster.dto.task;

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
public class CreateTaskDTO {

    private String name;

    private TaskStatus status;

    private TaskPriority priority;

    private Integer projectId;

    private Integer assigneeId;
}
