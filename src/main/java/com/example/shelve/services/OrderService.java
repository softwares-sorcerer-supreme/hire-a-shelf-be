package com.example.shelve.services;

import com.example.shelve.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrder();
}
