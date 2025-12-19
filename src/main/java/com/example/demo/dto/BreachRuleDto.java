package com.example.demo.dto;
import lombok.*;
import java.math.BigDecimal;

@Data
public class BreachRuleDto {
    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
}
