package com.example.shelve.services.impl;

import com.example.shelve.repository.ContractRepository;
import com.example.shelve.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;
}
