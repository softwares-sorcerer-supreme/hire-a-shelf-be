package com.example.shelve.services.impl;

import com.example.shelve.repository.RetailerRepository;
import com.example.shelve.services.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;

public class RetailerServiceImpl implements RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;
}
