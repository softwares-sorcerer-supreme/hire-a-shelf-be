package com.example.shelve.services.impl;

import com.example.shelve.dto.request.OrderRequest;
import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.entities.*;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.OrderMapper;
import com.example.shelve.repository.*;
import com.example.shelve.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderResponse> getAllOrder() {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderRepository.findAll().forEach(o -> orderResponseList.add(orderMapper.toOrderResponse(o)));

        return orderResponseList;
    }

    @Override
    public OrderResponse createNewOrder(OrderRequest orderRequest) {
        Store store = storeRepository.findById(orderRequest.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found!"));

        Campaign campaign = campaignRepository.findById(orderRequest.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        Order order = orderRepository.save(
                Order.builder()
                        .date(new Date(System.currentTimeMillis()))
                        .store(store)
                        .campaign(campaign)
                        .build()
        );

        orderRequest.getProductSales().forEach((key, value) -> {
            Product product = productRepository.findById(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

            orderDetailRepository.save(OrderDetail.builder()
                    .order(order)
                    .sale(value)
                    .product(product)
                    .build()
            );
        });

        Order newOrder = orderRepository.findById(order.getId()).get();

        return orderMapper.toOrderResponse(newOrder);
    }


}
