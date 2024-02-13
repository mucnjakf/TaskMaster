package com.devlab.taskmaster.dto.user;

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
public class UserTaskDTO {

    private Integer id;

    private String name;

    private TaskStatus status;

    private TaskPriority priority;
}
