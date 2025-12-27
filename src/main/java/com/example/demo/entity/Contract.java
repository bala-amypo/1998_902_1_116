package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;

    private LocalDate startDate;

    private LocalDate endDate;

    public Contract() {
    }

    public Contract(Long id, String contractNumber, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getContractNumber() {
        return contractNumber;
    }
 
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
 
    public LocalDate getStartDate() {
        return startDate;
    }
 
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
 
    public LocalDate getEndDate() {
        return endDate;
    }
 
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
