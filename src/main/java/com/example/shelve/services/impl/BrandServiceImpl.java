package com.example.shelve.services.impl;

import com.example.shelve.repository.BrandRepository;
import com.example.shelve.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
}
