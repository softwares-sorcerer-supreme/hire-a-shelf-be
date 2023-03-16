package com.example.shelve.services.impl;

import com.example.shelve.dto.response.StoreResponse;
import com.example.shelve.entities.Store;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.StoreMapper;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public StoreResponse getStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found!"));

        return storeMapper.toStoreResponse(store);
    }
}
