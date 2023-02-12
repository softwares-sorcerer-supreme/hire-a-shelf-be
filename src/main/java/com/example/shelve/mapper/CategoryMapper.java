package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.CategoryResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Category;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class CategoryMapper {
    public CategoryResponse toCategoryResponse (Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .name(category.getName())
                .status(category.isStatus())
                .products(category.getProducts())
                .build();

        return categoryResponse;
    }
}
