package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    private String conditionType;

    private int threshold;

    public BreachRule() {
    }

    public BreachRule(Long id, String ruleName, String conditionType, int threshold) {
        this.id = id;
        this.ruleName = ruleName;
        this.conditionType = conditionType;
        this.threshold = threshold;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getRuleName() {
        return ruleName;
    }
 
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
 
    public String getConditionType() {
        return conditionType;
    }
 
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
 
    public int getThreshold() {
        return threshold;
    }
 
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
