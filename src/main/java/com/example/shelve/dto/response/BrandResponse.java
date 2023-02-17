package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Product;
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

    private String address;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private boolean status;

//    private Set<CampaignResponse> campaigns;

//    private Set<ProductResponse> products;

//    private AccountResponse account;
}
