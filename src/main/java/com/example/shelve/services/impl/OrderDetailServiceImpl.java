package com.example.shelve.services.impl;

import com.example.shelve.repository.OrderDetailRepository;
import com.example.shelve.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
}
