package com.example.demo.dto;
import lombok.*;
import java.math.BigDecimal;

@Data
public class BreachReportDto {
    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
}
