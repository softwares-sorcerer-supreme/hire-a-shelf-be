package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CategoryRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.CategoryResponse;
import com.example.shelve.entities.Category;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CategoryMapper;
import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return mapper.toCategoryResponse(categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!")));
    }

    @Override
    @Cacheable(value = "category")
    public List<CategoryResponse> getCategoriesByStatus(boolean status) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categoryRepository.findCategoriesByStatus(status).forEach(x -> categoryResponses.add(mapper.toCategoryResponse(x)));
        return categoryResponses;
    }

    @Override
    @CacheEvict(value = "category", allEntries = true)
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category savedCategory = new Category();
        savedCategory.setName(categoryRequest.getName());
        savedCategory.setDescription(categoryRequest.getDescription());
        savedCategory.setStatus(true);
        return mapper.toCategoryResponse(categoryRepository.save(savedCategory));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Optional<Category> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isEmpty()){
            throw new BadRequestException("Category is not found!");
        }else{
            categoryFound.get().setName(categoryRequest.getName());
            categoryFound.get().setDescription(categoryRequest.getDescription());
            Category categorySaved = categoryRepository.save(categoryFound.get());
            return mapper.toCategoryResponse(categorySaved);
        }
    }
}
