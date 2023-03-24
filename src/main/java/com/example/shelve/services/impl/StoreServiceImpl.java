package com.example.shelve.services.impl;

import com.example.shelve.dto.request.StoreCategoryRequest;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.StoreCategoryResponse;
import com.example.shelve.dto.response.StoreResponse;
import com.example.shelve.entities.Category;
import com.example.shelve.entities.Store;
import com.example.shelve.entities.StoreCategory;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.StoreCategoryMapper;
import com.example.shelve.mapper.StoreMapper;
import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.repository.StoreCategoryRepository;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private StoreCategoryRepository storeCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StoreCategoryMapper storeCategoryMapper;
    @Override
    public StoreResponse getStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found!"));

        return storeMapper.toStoreResponse(store);
    }

    @Override
    public List<StoreCategoryResponse> addFavouriteCategory(StoreCategoryRequest storeCategoryRequest) {
        List<StoreCategoryResponse> storeCategoryResponses = new ArrayList<>();
        Optional<Store> storeFound = storeRepository.findById(storeCategoryRequest.getStoreId());
        if (storeFound.isEmpty()){
            throw new NotFoundException("Store with id " + storeCategoryRequest.getStoreId() + " could not be found!");
        }
        storeCategoryRequest.getCategoriesId().forEach(categoryId -> {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if(category.isEmpty()){
                throw new NotFoundException("Category with id " + categoryId + " could not be found!");
            }else{
                if (storeCategoryRepository.findByStoreIdAndCategoryId(storeCategoryRequest.getStoreId(), categoryId).isEmpty()){
                    StoreCategory storeCategory = StoreCategory.builder()
                            .category(category.get())
                            .store(storeFound.get())
                            .build();
                    storeCategoryResponses.add
                            (storeCategoryMapper.toStoreCategoryResponse(storeCategoryRepository.save(storeCategory)));
                }
            }
        });

        return storeCategoryResponses;
    }

    @Override
    public List<StoreResponse> getAllStore() {
        List<StoreResponse> storeResponses = new ArrayList<>();
        storeRepository.findAll().forEach(x -> storeResponses.add(storeMapper.toStoreResponse(x)));
        return storeResponses;
    }
}
