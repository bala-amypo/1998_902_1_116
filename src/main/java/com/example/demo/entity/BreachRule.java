package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_rules")
public class BreachRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleName;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyPerDay;
    
    @Column(nullable = false)
    private Double maxPenaltyPercentage;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    private Boolean isDefaultRule;
    
    public BreachRule() {}
    
    public BreachRule(String ruleName, BigDecimal penaltyPerDay, Double maxPenaltyPercentage) {
        this.ruleName = ruleName;
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    
    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public void setPenaltyPerDay(BigDecimal penaltyPerDay) { this.penaltyPerDay = penaltyPerDay; }
    
    public Double getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage) { this.maxPenaltyPercentage = maxPenaltyPercentage; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Boolean getIsDefaultRule() { return isDefaultRule; }
    public void setIsDefaultRule(Boolean isDefaultRule) { this.isDefaultRule = isDefaultRule; }
}