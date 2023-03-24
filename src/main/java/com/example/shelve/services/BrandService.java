package com.example.shelve.services;

import com.example.shelve.dto.request.BrandRequest;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;

import java.util.List;

public interface BrandService {

    List<BrandResponse> getAllBrand();

    BrandResponse getBrand(Long id);

    BrandResponse updateBrand(Long id, BrandRequest brandRequest);

    BrandResponse deleteBrand(Long id);

}
