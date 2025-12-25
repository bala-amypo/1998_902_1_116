package com.example.demo.service;

import com.example.demo.dto.ContractDto;
import com.example.demo.entity.Contract;

import java.util.List;

public interface ContractService {

    Contract createContract(ContractDto dto);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);
}
