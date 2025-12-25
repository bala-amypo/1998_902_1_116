package com.example.demo.service.impl;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final ContractRepository contractRepository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord addDeliveryRecord(DeliveryRecordDto dto) {
        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord record = new DeliveryRecord();
        record.setContract(contract);
        record.setDeliveryDate(dto.getDeliveryDate());
        record.setNotes(dto.getNotes());

        return deliveryRecordRepository.save(record);
    }
}
