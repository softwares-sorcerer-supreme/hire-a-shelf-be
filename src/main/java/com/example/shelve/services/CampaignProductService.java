package com.example.shelve.services;

import com.example.shelve.dto.response.CampaignProductResponse;

import java.util.List;

public interface CampaignProductService {

    public List<CampaignProductResponse> getProductsByCampaignId(Long id);
}
