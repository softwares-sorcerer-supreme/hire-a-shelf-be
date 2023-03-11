package com.example.shelve.mapper;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.CampaignProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampaignProductMapper {
    @Autowired
    private CampaignMapper campaignMapper;
    @Autowired
    private ProductMapper productMapper;
    public CampaignProductResponse toCampaignProductResponse (CampaignProduct campaignProduct){
        return CampaignProductResponse.builder()
                .productResponse(productMapper.toProductResponse(campaignProduct.getProduct()))
                .campaignResponse(campaignMapper.toCampaignResponse(campaignProduct.getCampaign()))
                .build();
    }
}
