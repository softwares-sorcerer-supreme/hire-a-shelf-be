package com.example.shelve.dto.request;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Store;
import com.example.shelve.entities.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    private Date cancelDate;
    private String description;
    private Date approvalDate;
    private EStatus eStatus;
    private Long campaignId;
    private Long storeId;

}
