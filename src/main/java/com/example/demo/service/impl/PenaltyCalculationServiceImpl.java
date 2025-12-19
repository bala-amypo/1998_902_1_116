package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;
    private final PenaltyCalculationRepository penaltyRepo;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo,
            PenaltyCalculationRepository penaltyRepo) {

        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
        this.penaltyRepo = penaltyRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId).orElseThrow();
        DeliveryRecord delivery = deliveryRepo.findByContract(contract).orElseThrow();
        BreachRule rule = ruleRepo.findByIsDefaultRuleTrue().orElseThrow();

        long delayDays = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                delivery.getDeliveryDate());

        if (delayDays <= 0) delayDays = 0;

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(delayDays));

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setContract(contract);
        pc.setDaysDelayed((int) delayDays);
        pc.setCalculatedPenalty(penalty);

        return penaltyRepo.save(pc);
    }
}
