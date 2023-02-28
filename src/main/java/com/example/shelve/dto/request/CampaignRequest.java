package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {
    private String title;
    private String content;
    private Date createdDate;
    private Date startDate;
    private Date expirationDate;
    private int duration;
    private MultipartFile imgMultipart;
    private Brand brand;
    private EStatus EStatus;
    private List<Long> products;
    private List<Long> shelveTypes;
}
