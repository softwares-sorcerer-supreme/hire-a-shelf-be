package com.example.shelve.dto.response;

import com.example.shelve.entities.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse {

    private Long id;

    private String title;

    private String content;

    private Date createdDate;

    private Date startDate;

    private Date expirationDate;

    private int duration;

    private String imgURL;

    private BrandResponse brand;

    private Set<ContractResponse> contracts;

    private Set<OrderResponse> orders;

    private Set<ProductResponse> products;


}
