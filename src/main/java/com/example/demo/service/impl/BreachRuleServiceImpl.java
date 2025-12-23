package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {
    
    private final BreachRuleRepository breachRuleRepository;
    
    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }
    
    @Override
    public BreachRule createRule(BreachRule rule) {
        if (breachRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new BadRequestException("Rule planName already exists");
        }
        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Penalty per day must be greater than 0");
        }
        if (rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Max penalty percentage must be between 0 and 100");
        }
        return breachRuleRepository.save(rule);
    }
    
    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = breachRuleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Breach rule not found"));
        
        existing.setRuleName(rule.getRuleName());
        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Penalty per day must be greater than 0");
        }
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        if (rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Max penalty percentage must be between 0 and 100");
        }
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setActive(rule.getActive());
        existing.setIsDefaultRule(rule.getIsDefaultRule());
        return breachRuleRepository.save(existing);
    }
    
    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));
    }
    
    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
    
    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Breach rule not found"));
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}