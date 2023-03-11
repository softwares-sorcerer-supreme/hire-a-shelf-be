package com.example.shelve.services;

import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();

    ProductResponse creteProduct(ProductRequest productRequest);

    APIResponse<List<ProductResponse>> getAllProductWithFilter(long brandId, int page, String keyword, List<Long> categoriesId);

    List<ProductResponse> getAllBrandsAvailableProductWithoutFilter(long brandId);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);
}
