package com.example.shelve.services;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;

import java.util.List;

public interface ContractService {

    List<ContractResponse> getAllContract();

    ContractResponse getContract(Long id);
}
