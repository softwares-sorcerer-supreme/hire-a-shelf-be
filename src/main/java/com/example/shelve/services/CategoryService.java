package com.example.shelve.services;

import com.example.shelve.dto.request.CategoryRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategory(Long id);

    List<CategoryResponse> getCategoriesByStatus(boolean status);

    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
