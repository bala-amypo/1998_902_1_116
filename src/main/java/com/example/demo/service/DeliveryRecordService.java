package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {
    DeliveryRecord saveDelivery(DeliveryRecord record);
    List<DeliveryRecord> getByContractId(Long contractId);
}
