package com.example.demo.controller;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    public DeliveryRecord add(@RequestBody DeliveryRecordDto dto) {
        return deliveryRecordService.addDeliveryRecord(dto);
    }
}
