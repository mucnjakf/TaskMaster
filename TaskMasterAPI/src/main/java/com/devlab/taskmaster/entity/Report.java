package com.devlab.taskmaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime generatedAt;

    private Integer totalTasks;

    private Integer tasksInBacklog;

    private Integer tasksInProgress;

    private Integer tasksCompleted;
}
