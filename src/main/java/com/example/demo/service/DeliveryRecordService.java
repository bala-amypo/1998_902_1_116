package com.example.demo.service;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.DeliveryRecord;

public interface DeliveryRecordService {

    DeliveryRecord addDeliveryRecord(DeliveryRecordDto dto);
}
