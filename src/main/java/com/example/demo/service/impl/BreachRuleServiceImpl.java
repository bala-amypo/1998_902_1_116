package com.example.demo.service.impl;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRuleDto dto) {
        BreachRule rule = new BreachRule();
        rule.setRuleName(dto.getRuleName());
        rule.setPenaltyPerDay(dto.getPenaltyPerDay());
        rule.setMaxPenaltyPercentage(dto.getMaxPenaltyPercentage());
        rule.setIsDefaultRule(dto.getIsDefaultRule());

        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getDefaultRule() {
        return breachRuleRepository.findByIsDefaultRuleTrue()
                .orElseThrow(() -> new RuntimeException("Default rule not found"));
    }
}
