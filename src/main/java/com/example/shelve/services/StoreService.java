package com.example.shelve.services;

import com.example.shelve.dto.request.StoreCategoryRequest;
import com.example.shelve.dto.response.StoreCategoryResponse;
import com.example.shelve.dto.response.StoreResponse;

import java.util.List;

public interface StoreService {
    StoreResponse getStore(Long id);

    List<StoreCategoryResponse> addFavouriteCategory(StoreCategoryRequest storeCategoryRequest);

    List<StoreResponse> getAllStore();
}
