package com.example.shelve.dto.response;

import com.example.shelve.entities.Order;
import com.example.shelve.entities.Products;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private long id;

    private String sales;

    private Products products;

    private Order order;
}
