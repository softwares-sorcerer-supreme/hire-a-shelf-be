package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {

    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private Date startDate;
    private Date expirationDate;
    private int duration;
    private String imgURL;
    private Brand brand;
    private EStatus EStatus;
    private List<Long> products;
}
