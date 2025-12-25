package com.example.demo.controller;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRuleDto dto) {
        return breachRuleService.createRule(dto);
    }

    @GetMapping("/default")
    public BreachRule getDefaultRule() {
        return breachRuleService.getDefaultRule();
    }
}
