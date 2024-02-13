package com.devlab.taskmaster.serviceTests;

import com.devlab.taskmaster.data.ReportMockData;
import com.devlab.taskmaster.data.TaskMockData;
import com.devlab.taskmaster.dto.report.ReportDTO;
import com.devlab.taskmaster.repository.ReportRepository;
import com.devlab.taskmaster.service.ReportService;
import com.devlab.taskmaster.service.TaskService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ReportServiceTests {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private ReportService sut;

    @Test
    public void getReports_should_return_reports() {
        when(reportRepository.findAll()).thenReturn(ReportMockData.getReports());

        List<ReportDTO> reports = sut.getReports();

        assertNotNull(reports);
        assertEquals(1, reports.size());
        verify(reportRepository).findAll();
    }

    @Test
    public void createReport_should_create_and_return_report() {
        when(taskService.getTaskCount()).thenReturn(TaskMockData.getTaskCountDTO());

        ReportDTO report = sut.createReport();

        assertNotNull(report);
        assertEquals(TaskMockData.getTaskCountDTO().getTotalTasks(), report.getTotalTasks());
        verify(reportRepository).save(any());
    }

    @Test
    public void deleteReport_should_delete() {
        when(reportRepository.findById(any())).thenReturn(Optional.of(ReportMockData.getReport()));

        sut.deleteReport(any());

        verify(reportRepository).findById(any());
        verify(reportRepository).delete(any());
    }
}
