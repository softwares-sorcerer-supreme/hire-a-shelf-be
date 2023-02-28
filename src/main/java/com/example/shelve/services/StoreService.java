package com.example.shelve.services;

import com.example.shelve.dto.response.StoreResponse;

public interface StoreService {
    StoreResponse getStore(Long id);
}
