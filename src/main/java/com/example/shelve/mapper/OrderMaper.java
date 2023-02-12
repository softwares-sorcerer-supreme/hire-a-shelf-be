package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Order;

public class OrderMaper {

    public OrderResponse toOrderResponse (Order order){
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .date(order.getDate())
                .campaign(order.getCampaign())
                .store(order.getStore())
                .build();

        return orderResponse;
    }
}
