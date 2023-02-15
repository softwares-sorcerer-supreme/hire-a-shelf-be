package com.example.shelve.dto.request;

import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Shelves;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {

    private String title;

    private String content;

    private Date createdDate;

    private Date expirationDate;

    private int duration;

    private String imgURL;
    private Brand brand;
    private Shelves shelves;
}
