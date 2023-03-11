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
    CampaignMapper campaignMapper;

    @Autowired
    StoreMapper storeMapper;


    public ContractResponse toContractResponse (Contract contract){
        ContractResponse contractResponse = ContractResponse.builder()
                .id(contract.getId())
                .cancelDate(contract.getCancelDate())
                .createDate(contract.getCreateDate())
                .description(contract.getDescription())
                .status(contract.getEStatus().getName())
                .approvalDate(contract.getApprovalDate())
                .campaign(campaignMapper.toCampaignResponse(contract.getCampaign()))
                .store(storeMapper.toStoreResponse(contract.getStore()))
                .build();

        return contractResponse;
    }

//    public Contract toContract(ContractRequest contractRequest) {
//        return Contract.builder()
//                .store(contractRequest.getStore())
//                .campaign(contractRequest.getCampaign())
//                .description(contractRequest.getDescription())
//                .build();
//    }
}
