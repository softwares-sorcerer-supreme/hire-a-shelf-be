package com.example.shelve.dto.response;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.OrderDetail;
import com.example.shelve.entities.Store;
import lombok.*;

import java.sql.Date;
import java.util.Set;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;

    private Date date;

    private Set<OrderDetail> orderDetails;

}
