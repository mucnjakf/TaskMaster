package com.devlab.taskmaster.job;

import com.devlab.taskmaster.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyReportJob {

    private final ReportService reportService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyReport() {
        reportService.createReport();
    }
}
