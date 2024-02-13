package com.devlab.taskmaster.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private Integer id;

    private String generatedAt;

    private Integer totalTasks;

    private Integer tasksInBacklog;

    private Integer tasksInProgress;

    private Integer tasksCompleted;
}
