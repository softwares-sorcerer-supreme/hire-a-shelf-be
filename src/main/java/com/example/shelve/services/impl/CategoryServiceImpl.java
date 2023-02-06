package com.example.shelve.services.impl;

import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
}
