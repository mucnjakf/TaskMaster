package com.devlab.taskmaster.data;

import com.devlab.taskmaster.entity.Report;

import java.time.LocalDateTime;
import java.util.List;

public class ReportMockData {

    public static Report getReport() {
        return Report
                .builder()
                .id(1)
                .generatedAt(LocalDateTime.now())
                .totalTasks(5)
                .tasksInBacklog(3)
                .tasksInProgress(1)
                .tasksCompleted(1)
                .build();
    }

    public static List<Report> getReports() {
        return List.of(getReport());
    }

}
