package com.example.shelve.dto.response;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Store;
import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import java.sql.Date;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {

    private Long id;
    private Date createDate;
    private Date cancelDate;
    private Date approvalDate;
    private String description;
    private String status;
    private CampaignResponse campaignResponse;
    private StoreResponse storeResponse;
}
