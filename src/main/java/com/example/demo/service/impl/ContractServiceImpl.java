package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    
    public ContractServiceImpl(ContractRepository contractRepository, 
                              DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }
    
    @Override
    public Contract createContract(Contract contract) {
        if (contractRepository.findByContractNumber(contract.getContractNumber()).isPresent()) {
            throw new BadRequestException("Contract number already exists");
        }
        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Base contract value must be greater than 0");
        }
        return contractRepository.save(contract);
    }
    
    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = getContractById(id);
        existing.setTitle(contract.getTitle());
        existing.setCounterpartyName(contract.getCounterpartyName());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Base contract value must be greater than 0");
        }
        existing.setBaseContractValue(contract.getBaseContractValue());
        existing.setStatus(contract.getStatus());
        return contractRepository.save(existing);
    }
    
    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }
    
    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    
    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        // Logic to update status based on delivery records
        contractRepository.save(contract);
    }
}