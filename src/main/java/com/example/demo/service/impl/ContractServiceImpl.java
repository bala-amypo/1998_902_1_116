package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repository;

    public ContractServiceImpl(ContractRepository repository) {
        this.repository = repository;
    }

    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue().doubleValue() <= 0)
            throw new BadRequestException("Base contract value invalid");

        return repository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return repository.findAll();
    }
}
