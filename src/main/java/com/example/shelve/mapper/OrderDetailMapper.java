package com.example.shelve.mapper;

import com.example.shelve.dto.response.OrderDetailResponse;
import com.example.shelve.entities.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .product(orderDetail.getProduct())
                .sale(orderDetail.getSale())
                .build();
    }
}
