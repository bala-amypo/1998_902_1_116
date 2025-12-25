package com.example.demo.service;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;

public interface BreachRuleService {

    BreachRule createRule(BreachRuleDto dto);

    BreachRule getDefaultRule();
}
