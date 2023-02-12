package com.example.shelve.dto.response;

import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponse {

    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private Date expirationDate;
    private int duration;
    private Brand brand;
    private Set<Contract> contracts;
}
