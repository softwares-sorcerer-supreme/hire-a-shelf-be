package com.example.shelve.services.impl;

import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Category;
import com.example.shelve.entities.Product;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ProductMapper;
import com.example.shelve.repository.BrandRepository;
import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productResponseList.add(productMapper.toProductResponse(product)));

        return productResponseList;
    }

    @Override
    public ProductResponse creteProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        Brand brand = brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        Product product = productMapper.toProduct(productRequest);
        product.setCategory(category);
        product.setBrand(brand);

        Product productSaved = productRepository.save(product);

        return productMapper.toProductResponse(productSaved);
    }
}
