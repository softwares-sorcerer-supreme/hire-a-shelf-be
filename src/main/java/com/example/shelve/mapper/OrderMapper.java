package com.example.shelve.mapper;

import com.example.shelve.dto.response.OrderDetailResponse;
import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OrderMapper {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderResponse toOrderResponse(Order order) {
        Set<OrderDetailResponse> list = new HashSet<>();
        order.getOrderDetails().forEach(ord -> list.add(
                orderDetailMapper.toOrderDetailResponse(ord))
        );

        return OrderResponse.builder()
                .id(order.getId())
                .date(order.getDate())
                .orderDetails(list)
                .build();
    }

}
