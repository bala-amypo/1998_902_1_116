package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(ContractRepository contractRepository,
                                         DeliveryRecordRepository deliveryRecordRepository,
                                         BreachRuleRepository breachRuleRepository,
                                         PenaltyCalculationRepository penaltyCalculationRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord record = deliveryRecordRepository.findByContractId(contractId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        BreachRule rule = breachRuleRepository.findByIsDefaultRuleTrue()
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(Math.max(daysDelayed, 0)));

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDaysDelayed((int) daysDelayed);
        calculation.setCalculatedPenalty(penalty);

        return penaltyCalculationRepository.save(calculation);
    }
}
