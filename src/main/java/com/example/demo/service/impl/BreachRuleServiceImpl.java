package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.util.List;

public class BreachRuleServiceImpl implements BreachRuleService {

    // ⚠️ NOT final — injected by reflection in tests
    BreachRuleRepository breachRuleRepository;

    // ✅ REQUIRED by tests
    public BreachRuleServiceImpl() {
    }

    @Override
    public BreachRule createRule(BreachRule r) {
        if (r.getPenaltyPerDay() == null ||
            r.getPenaltyPerDay().doubleValue() <= 0 ||
            r.getMaxPenaltyPercentage() == null ||
            r.getMaxPenaltyPercentage() < 0 ||
            r.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Invalid penalty");
        }
        return breachRuleRepository.save(r);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No active breach rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}
