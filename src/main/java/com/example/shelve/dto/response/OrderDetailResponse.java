package com.example.shelve.dto.response;

import com.example.shelve.entities.Order;
import com.example.shelve.entities.Product;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private long id;
    private int sale;
    private ProductResponse product;
}
