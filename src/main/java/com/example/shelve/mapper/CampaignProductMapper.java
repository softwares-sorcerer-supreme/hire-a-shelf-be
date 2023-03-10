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
    ProductMapper productMapper;

    @Autowired
    CampaignMapper campaignMapper;

    public CampaignProductResponse toCampaignProductResponse (CampaignProduct campaignProduct){
        CampaignProductResponse campaignProductResponse = CampaignProductResponse.builder()
                .products(productMapper.toProductResponse(campaignProduct.getProduct()))
                .campaigns(campaignMapper.toCampaignResponse(campaignProduct.getCampaign()))
                .build();

        return campaignProductResponse;
    }
}
