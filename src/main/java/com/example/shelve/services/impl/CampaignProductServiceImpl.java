package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.mapper.CampaignProductMapper;
import com.example.shelve.repository.CampaignProductRepository;
import com.example.shelve.services.CampaignProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CampaignProductServiceImpl implements CampaignProductService {

    @Autowired
    private CampaignProductRepository campaignProductRepository;

    @Autowired
    private CampaignProductMapper mapper;

    @Override
    public List<CampaignProductResponse> getProductsByCampaignId(Long id) {
        List<CampaignProductResponse> campaignProductResponses = new ArrayList<>();
        campaignProductRepository.findCampaignProductByCampaign_Id(id)
                                 .forEach(x -> campaignProductResponses.add(mapper.toCampaignProductResponse(x)));
        return campaignProductResponses;
    }
}
