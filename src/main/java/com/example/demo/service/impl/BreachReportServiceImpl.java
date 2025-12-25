package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.BreachReportService;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final PenaltyCalculationService penaltyCalculationService;
    private final BreachReportRepository breachReportRepository;

    public BreachReportServiceImpl(ContractRepository contractRepository,
                                   PenaltyCalculationService penaltyCalculationService,
                                   BreachReportRepository breachReportRepository) {
        this.contractRepository = contractRepository;
        this.penaltyCalculationService = penaltyCalculationService;
        this.breachReportRepository = breachReportRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calculation = penaltyCalculationService.calculatePenalty(contractId);

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());

        return breachReportRepository.save(report);
    }
}
