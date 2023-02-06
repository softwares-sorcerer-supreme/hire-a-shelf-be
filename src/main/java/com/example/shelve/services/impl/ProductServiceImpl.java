package com.example.shelve.services.impl;

import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
}
