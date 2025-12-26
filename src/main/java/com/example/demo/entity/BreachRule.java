package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleName;
    
    @Column(nullable = false)
    private BigDecimal penaltyPerDay;
    
    @Column(nullable = false)
    private Double maxPenaltyPercentage;
    
    @Builder.Default
    private Boolean active = true;
    
    private Boolean isDefaultRule;
}