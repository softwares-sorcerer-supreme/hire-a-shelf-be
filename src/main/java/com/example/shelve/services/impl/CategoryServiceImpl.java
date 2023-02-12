package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.CategoryResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CategoryMapper;
import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categoryRepository.findAll().forEach(x -> categoryResponses.add(mapper.toCategoryResponse(x)));
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        CategoryResponse categoryResponse = mapper.toCategoryResponse(categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!")));
        ;
        return categoryResponse;
    }
}
