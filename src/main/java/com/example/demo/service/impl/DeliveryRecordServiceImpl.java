package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository recordRepo;
    private final ContractRepository contractRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository recordRepo,
            ContractRepository contractRepo) {
        this.recordRepo = recordRepo;
        this.contractRepo = contractRepo;
    }

    public DeliveryRecord create(DeliveryRecord record) {
        return recordRepo.save(record);
    }

    public List<DeliveryRecord> getByContract(Long contractId) {
        Contract contract = contractRepo.findById(contractId).orElseThrow();
        return recordRepo.findAll()
                .stream()
                .filter(r -> r.getContract().equals(contract))
                .toList();
    }
}
