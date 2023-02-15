package com.example.shelve.services;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProduct(Long id);
}
