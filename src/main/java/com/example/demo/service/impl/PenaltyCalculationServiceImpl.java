package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {
    
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    
    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyCalculationRepository,
                                       ContractRepository contractRepository,
                                       DeliveryRecordRepository deliveryRecordRepository,
                                       BreachRuleRepository breachRuleRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
    }
    
    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
        
        DeliveryRecord latestDelivery = deliveryRecordRepository
            .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
            .orElseThrow(() -> new ResourceNotFoundException("No delivery records found"));
        
        BreachRule rule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));
        
        int daysDelayed = (int) ChronoUnit.DAYS.between(contract.getAgreedDeliveryDate(), latestDelivery.getDeliveryDate());
        daysDelayed = Math.max(0, daysDelayed);
        
        BigDecimal penalty = rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));
        BigDecimal maxPenalty = contract.getBaseContractValue()
            .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
            .divide(BigDecimal.valueOf(100));
        
        penalty = penalty.min(maxPenalty);
        
        PenaltyCalculation calculation = new PenaltyCalculation(contract, latestDelivery, rule, daysDelayed, penalty);
        return penaltyCalculationRepository.save(calculation);
    }
    
    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Penalty calculation not found"));
    }
    
    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}