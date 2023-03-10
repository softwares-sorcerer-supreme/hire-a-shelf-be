package com.example.shelve.services;

import com.example.shelve.dto.request.OrderDetailRequest;
import com.example.shelve.dto.response.OrderDetailResponse;

public interface OrderDetailService {
    OrderDetailResponse updateOrderDetailService(Long id, OrderDetailRequest orderDetailRequest);
}
