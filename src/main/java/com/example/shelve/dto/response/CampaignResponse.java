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

    private Date expirationDate;

    private int duration;

    private String imgURL;

    private Brand brand;

    private Set<Contract> contracts;

    private Shelves shelves;

    private Set<Order> orders;

    private Set<Products> products;


}
