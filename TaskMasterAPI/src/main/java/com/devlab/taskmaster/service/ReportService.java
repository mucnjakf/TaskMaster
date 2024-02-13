package com.devlab.taskmaster.service;

import com.devlab.taskmaster.dto.report.ReportDTO;
import com.devlab.taskmaster.dto.task.TaskCountDTO;
import com.devlab.taskmaster.entity.Report;
import com.devlab.taskmaster.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final TaskService taskService;

    public List<ReportDTO> getReports() {
        Iterable<Report> reports = reportRepository.findAll();
        List<ReportDTO> reportDTOs = new ArrayList<>();

        for (Report report : reports) {
            reportDTOs.add(mapReportToReportDTO(report));
        }

        return reportDTOs;
    }

    public ReportDTO createReport() {
        TaskCountDTO taskCount = taskService.getTaskCount();

        Report report = Report
                .builder()
                .generatedAt(LocalDateTime.now())
                .totalTasks(taskCount.getTotalTasks())
                .tasksInBacklog(taskCount.getTasksInBacklog())
                .tasksInProgress(taskCount.getTasksInProgress())
                .tasksCompleted(taskCount.getTasksCompleted())
                .build();

        reportRepository.save(report);

        return mapReportToReportDTO(report);
    }

    public void deleteReport(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));

        reportRepository.delete(report);
    }

    private ReportDTO mapReportToReportDTO(Report report) {
        return ReportDTO
                .builder()
                .id(report.getId())
                .generatedAt(report.getGeneratedAt().toString())
                .totalTasks(report.getTotalTasks())
                .tasksInBacklog(report.getTasksInBacklog())
                .tasksInProgress(report.getTasksInProgress())
                .tasksCompleted(report.getTasksCompleted())
                .build();
    }
}
