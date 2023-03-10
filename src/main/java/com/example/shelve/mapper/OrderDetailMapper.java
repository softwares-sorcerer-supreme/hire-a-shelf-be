package com.example.shelve.mapper;

import com.example.shelve.dto.response.OrderDetailResponse;
import com.example.shelve.entities.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    @Autowired
    private ProductMapper productMapper;

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .product(productMapper.toProductResponse(orderDetail.getProduct()))
                .sale(orderDetail.getSale())
                .build();
    }
}
