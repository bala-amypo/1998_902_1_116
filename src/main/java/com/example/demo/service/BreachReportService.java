package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import java.util.List;

public interface BreachReportService {
    BreachReport generateReport(BreachReport report);
    List<BreachReport> getReportsByContract(Long contractId);
}
