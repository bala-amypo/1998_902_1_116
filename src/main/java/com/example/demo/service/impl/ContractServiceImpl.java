package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    
    public ContractServiceImpl(ContractRepository contractRepository, DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }
    
    public ContractServiceImpl() {
        this.contractRepository = null;
        this.deliveryRecordRepository = null;
    }
    
    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than 0");
        }
        return contractRepository.save(contract);
    }
    
    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = getContractById(id);
        existing.setTitle(contract.getTitle());
        existing.setCounterpartyName(contract.getCounterpartyName());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        existing.setBaseContractValue(contract.getBaseContractValue());
        return contractRepository.save(existing);
    }
    
    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
    }
    
    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    
    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        Optional<DeliveryRecord> latestDelivery = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);
        
        if (latestDelivery.isPresent()) {
            LocalDate deliveryDate = latestDelivery.get().getDeliveryDate();
            if (deliveryDate.isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("COMPLETED");
            }
        } else {
            if (LocalDate.now().isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("ACTIVE");
            }
        }
        contractRepository.save(contract);
    }
}