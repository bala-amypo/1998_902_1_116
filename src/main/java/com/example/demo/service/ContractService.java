package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {
    Contract createContract(Contract contract);
    List<Contract> getAllContracts();
}
