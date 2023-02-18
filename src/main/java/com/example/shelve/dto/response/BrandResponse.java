package com.example.shelve.dto.response;

import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse implements Serializable {
    private Long id;
    private String name;

    private Set<LocationResponse> location;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private String EStatus;

//    private Set<CampaignResponse> campaigns;

//    private Set<ProductResponse> products;

//    private AccountResponse account;
}
