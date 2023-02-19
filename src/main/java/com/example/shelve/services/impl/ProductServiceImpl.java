package com.example.shelve.services.impl;

import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.mapper.ProductMapper;
import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productResponseList.add(productMapper.toProductResponse(product)));

        return productResponseList;
    }
}
