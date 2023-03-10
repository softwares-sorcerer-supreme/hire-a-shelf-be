package com.example.shelve.services.impl;

import com.example.shelve.dto.request.OrderDetailRequest;
import com.example.shelve.dto.response.OrderDetailResponse;
import com.example.shelve.entities.OrderDetail;
import com.example.shelve.entities.Product;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.OrderDetailMapper;
import com.example.shelve.repository.OrderDetailRepository;
import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailResponse updateOrderDetailService(Long id, OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail not found!"));

        orderDetailRequest.getProductSales().forEach((key, value) -> {
            productRepository.findById(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

            orderDetail.setSale(value);
        });

        OrderDetail orderDetailSaved = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetailSaved);
    }
}
