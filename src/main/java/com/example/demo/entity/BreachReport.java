package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportName;

    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    public BreachReport() {
    }

    public BreachReport(Long id, String reportName, LocalDateTime generatedAt, Contract contract) {
        this.id = id;
        this.reportName = reportName;
        this.generatedAt = generatedAt;
        this.contract = contract;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getReportName() {
        return reportName;
    }
 
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
 
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
 
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
 
    public Contract getContract() {
        return contract;
    }
 
    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
