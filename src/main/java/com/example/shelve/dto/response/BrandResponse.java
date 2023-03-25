package com.example.shelve.dto.response;

import com.example.shelve.entities.Location;
import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse implements Serializable {
    private Long id;
    private String name;

    private LocationResponse location;

    private String phone;
    
    private String logo;
    private Date participateDate;

    private String description;

    private boolean status;

//    private Set<CampaignResponse> campaigns;

//    private Set<ProductResponse> products;

//    private AccountResponse account;
}
