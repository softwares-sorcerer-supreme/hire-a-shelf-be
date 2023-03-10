package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequest {

    private Long storeId;
    private Long campaignId;

               //Product //Sale
    private HashMap<Long, Integer> productSales;

}
