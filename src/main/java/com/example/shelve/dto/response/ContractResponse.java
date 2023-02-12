package com.example.shelve.dto.response;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Store;
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

    private String description;

    private boolean status;

    private Campaign campaign;

    private Store store;
}
