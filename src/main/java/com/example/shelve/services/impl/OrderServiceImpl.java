package com.example.shelve.services.impl;

import com.example.shelve.repository.OrderRepository;
import com.example.shelve.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
}
