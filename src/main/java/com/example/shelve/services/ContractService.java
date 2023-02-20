package com.example.shelve.services;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.request.ContractRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.enums.EStatus;

import java.util.List;

public interface ContractService {

    List<ContractResponse> getAllContract();

    ContractResponse getContract(Long id);

    ContractResponse createContract(ContractRequest contractRequest);

    ContractResponse approveContract(Long id, EStatus status);

    ContractResponse disableContract(Long id);
}
