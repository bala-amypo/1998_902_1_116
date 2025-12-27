package com.example.demo.dto;

public class BreachRuleRequestDto {

    private String ruleName;
    private String description;
    private int threshold;

    public BreachRuleRequestDto() {
    }

    public BreachRuleRequestDto(String ruleName, String description, int threshold) {
        this.ruleName = ruleName;
        this.description = description;
        this.threshold = threshold;
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
