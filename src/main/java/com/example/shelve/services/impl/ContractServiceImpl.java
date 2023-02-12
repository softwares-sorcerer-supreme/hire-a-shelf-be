package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ContractMapper;
import com.example.shelve.repository.ContractRepository;
import com.example.shelve.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractMapper mapper;


    @Override
    public List<ContractResponse> getAllContract() {
        List<ContractResponse> contractResponses = new ArrayList<>();
        contractRepository.findAll().forEach(x -> contractResponses.add(mapper.toContractResponse(x)));
        return contractResponses;
    }

    @Override
    public ContractResponse getContract(Long id) {
        ContractResponse contractResponse = mapper.toContractResponse(contractRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found!")));
        return contractResponse;
    }
}
