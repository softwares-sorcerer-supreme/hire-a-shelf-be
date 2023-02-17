package com.example.shelve.dto.response;

import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse implements Serializable {
    private Long id;
    private String name;

    private String address;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private String EStatus;

//    private Set<CampaignResponse> campaigns;

//    private Set<ProductResponse> products;

//    private AccountResponse account;
}
