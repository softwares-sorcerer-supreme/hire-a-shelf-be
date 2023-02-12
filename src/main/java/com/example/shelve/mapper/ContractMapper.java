package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Contract;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class ContractMapper {

    public ContractResponse toContractResponse (Contract contract){
        ContractResponse contractResponse = ContractResponse.builder()
                .id(contract.getId())
                .cancelDate(contract.getCancelDate())
                .createDate(contract.getCreateDate())
                .description(contract.getDescription())
                .status(contract.isStatus())
                .campaign(contract.getCampaign())
                .store(contract.getStore())
                .build();

        return contractResponse;
    }
}
