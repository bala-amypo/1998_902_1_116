package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "penalty_calculations")
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double penaltyAmount;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    public PenaltyCalculation() {
    }

    public PenaltyCalculation(Long id, double penaltyAmount, String reason, Contract contract) {
        this.id = id;
        this.penaltyAmount = penaltyAmount;
        this.reason = reason;
        this.contract = contract;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public double getPenaltyAmount() {
        return penaltyAmount;
    }
 
    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
 
    public String getReason() {
        return reason;
    }
 
    public void setReason(String reason) {
        this.reason = reason;
    }
 
    public Contract getContract() {
        return contract;
    }
 
    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
