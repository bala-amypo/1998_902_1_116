package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final PenaltyCalculationRepository penaltyRepo;
    private final BreachReportRepository reportRepo;

    public BreachReportServiceImpl(
            PenaltyCalculationRepository penaltyRepo,
            BreachReportRepository reportRepo) {
        this.penaltyRepo = penaltyRepo;
        this.reportRepo = reportRepo;
    }

    public BreachReport generateReport(Long contractId) {

        PenaltyCalculation pc = penaltyRepo.findAll()
                .stream()
                .filter(p -> p.getContract().getId().equals(contractId))
                .reduce((first, second) -> second)
                .orElseThrow();

        BreachReport report = new BreachReport();
        report.setContract(pc.getContract());
        report.setDaysDelayed(pc.getDaysDelayed());
        report.setPenaltyAmount(pc.getCalculatedPenalty());

        return reportRepo.save(report);
    }
}
