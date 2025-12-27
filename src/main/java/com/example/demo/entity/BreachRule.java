package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    private String description;

    @Column(nullable = false)
    private int threshold;

    public BreachRule() {
    }

    public BreachRule(Long id, String ruleName, String description, int threshold) {
        this.id = id;
        this.ruleName = ruleName;
        this.description = description;
        this.threshold = threshold;
    }

    public BreachRule(String ruleName, String description, int threshold) {
        this.ruleName = ruleName;
        this.description = description;
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
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public int getThreshold() {
        return threshold;
    }
 
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
