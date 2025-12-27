package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

public interface PenaltyCalculationService {
    PenaltyCalculation savePenalty(PenaltyCalculation penalty);
    List<PenaltyCalculation> getByContractId(Long contractId);
}
