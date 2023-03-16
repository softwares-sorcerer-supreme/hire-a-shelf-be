package com.example.shelve.mapper;

import com.example.shelve.dto.request.ContractRequest;
import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Contract;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Builder
@Component
public class ContractMapper {
    @Autowired
    private CampaignMapper campaignMapper;
    @Autowired
    private StoreMapper storeMapper;

    public ContractResponse toContractResponse(Contract contract) {
        return ContractResponse.builder()
                .id(contract.getId())
                .cancelDate(contract.getCancelDate())
                .createDate(contract.getCreateDate())
                .description(contract.getDescription())
                .status(contract.getEStatus().getName())
                .approvalDate(contract.getApprovalDate())
                .campaignResponse(campaignMapper.toCampaignResponse(contract.getCampaign()))
                .storeResponse(storeMapper.toStoreResponse(contract.getStore()))
                .build();
    }

//    public Contract toContract(ContractRequest contractRequest) {
//        return Contract.builder()
//                .store(contractRequest.getStore())
//                .campaign(contractRequest.getCampaign())
//                .description(contractRequest.getDescription())
//                .build();
//    }
}
