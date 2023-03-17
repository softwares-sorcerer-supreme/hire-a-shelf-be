package com.example.shelve.mapper;

import com.example.shelve.dto.response.StoreCategoryResponse;
import com.example.shelve.entities.StoreCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreCategoryMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    StoreMapper storeMapper;
    public StoreCategoryResponse toStoreCategoryResponse(StoreCategory storeCategory){
        if (storeCategory == null){
            return null;
        }

        return StoreCategoryResponse.builder()
                .categoryResponse(categoryMapper.toCategoryResponse(storeCategory.getCategory()))
                .storeResponse(storeMapper.toStoreResponse(storeCategory.getStore()))
                .build();
    }
}
