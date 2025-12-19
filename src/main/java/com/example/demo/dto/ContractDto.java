package com.example.demo.dto;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;

@Data
public class ContractDto {
    private Long id;
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
}
