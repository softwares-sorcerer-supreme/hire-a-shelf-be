package com.example.shelve.services.impl;

import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.mapper.OrderMapper;
import com.example.shelve.repository.OrderRepository;
import com.example.shelve.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getAllOrder() {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderRepository.findAll().forEach(o -> orderResponseList.add(orderMapper.toOrderResponse(o)));

        return orderResponseList;
    }
}
