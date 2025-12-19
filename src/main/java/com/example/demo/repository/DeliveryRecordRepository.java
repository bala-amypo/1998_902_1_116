package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import java.util.Optional;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {
    Optional<DeliveryRecord> findFirstByContractOrderByDeliveryDateDesc(Contract contract);
}
