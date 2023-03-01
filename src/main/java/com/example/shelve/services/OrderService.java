package com.example.shelve.services;

import com.example.shelve.dto.request.OrderRequest;
import com.example.shelve.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrder();

    OrderResponse createNewOrder(OrderRequest orderRequest);
}
