package com.example.shelve.services.impl;

import com.example.shelve.repository.ProductDrawersDetailsRepository;
import com.example.shelve.services.ProductDrawersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDrawersDetailsServiceImpl implements ProductDrawersDetailsService {

    @Autowired
    private ProductDrawersDetailsRepository productDrawersDetailsRepository;
}
