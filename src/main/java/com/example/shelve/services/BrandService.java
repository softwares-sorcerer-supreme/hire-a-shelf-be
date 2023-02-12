package com.example.shelve.services;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;

import java.util.List;

public interface BrandService {

    List<BrandResponse> getAllBrand();

    BrandResponse getBrand(Long id);
}
