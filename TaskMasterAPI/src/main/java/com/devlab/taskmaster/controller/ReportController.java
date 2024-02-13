package com.devlab.taskmaster.controller;

import com.devlab.taskmaster.dto.report.ReportDTO;
import com.devlab.taskmaster.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getReports() {
        List<ReportDTO> reports = reportService.getReports();

        return ResponseEntity.ok(reports);
    }

    @GetMapping("/generate")
    public ResponseEntity<ReportDTO> createReport() {
        ReportDTO report = reportService.createReport();

        return ResponseEntity.ok(report);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);

        return ResponseEntity.noContent().build();
    }
}
