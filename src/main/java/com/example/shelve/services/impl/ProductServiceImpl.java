package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ProductMapper;
import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses = new ArrayList<>();
        productRepository.findAll().forEach(x -> productResponses.add(mapper.toProductResponse(x)));
        return productResponses;
    }

    @Override
    public ProductResponse getProduct(Long id) {
        ProductResponse productResponse = mapper.toProductResponse(productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!")));

        return productResponse;
    }
}
