package com.devlab.taskmaster.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCountDTO {

    private Integer totalTasks;

    private Integer tasksInBacklog;

    private Integer tasksInProgress;

    private Integer tasksCompleted;
}
