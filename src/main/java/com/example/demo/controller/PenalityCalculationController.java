package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalty")
public class PenaltyCalculationController {

    private final PenaltyCalculationService service;

    public PenaltyCalculationController(PenaltyCalculationService service) {
        this.service = service;
    }

    @PostMapping("/{contractId}")
    public PenaltyCalculation calculate(@PathVariable Long contractId) {
        return service.calculatePenalty(contractId);
    }
}
