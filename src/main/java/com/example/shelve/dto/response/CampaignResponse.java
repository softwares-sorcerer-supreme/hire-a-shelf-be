package com.example.shelve.dto.response;

import lombok.*;

import java.sql.Date;
import java.util.List;
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
    private String city;
    private String imgURL;
    private BrandResponse brand;
    private String status;
    private List<StoreResponse> appliers;
    private Set<OrderResponse> orderResponseSet;
    private Set<ProductResponse> productResponseSet;
    private String suitable;
}
