package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repo;

    public BreachRuleServiceImpl(BreachRuleRepository repo) {
        this.repo = repo;
    }

    public BreachRule createRule(BreachRule rule) {
        return repo.save(rule);
    }

    public List<BreachRule> getAllRules() {
        return repo.findAll();
    }
}
