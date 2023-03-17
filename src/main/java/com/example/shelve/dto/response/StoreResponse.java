package com.example.shelve.dto.response;

import com.example.shelve.entities.Order;
import com.example.shelve.entities.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreResponse {
    private Long id;
    private String name;
    private String phone;
    private String logo;
    private String description;
    private Date participateDate;
    private boolean status;
    private Set<ShelvesResponse> shelves;
    private Set<OrderResponse> orders;
    private LocationResponse location;

}
