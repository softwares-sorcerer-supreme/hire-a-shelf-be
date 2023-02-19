package com.example.shelve.services.impl;

import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
}
