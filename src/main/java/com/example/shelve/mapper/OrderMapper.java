package com.example.shelve.mapper;

import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .date(order.getDate())
                .orderDetails(order.getOrderDetails())
                .build();
    }

}
