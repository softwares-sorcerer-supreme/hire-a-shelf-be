package com.example.shelve.mapper;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.CampaignProduct;
import org.springframework.stereotype.Component;

@Component
public class CampaignProductMapper {

    public CampaignProductResponse toCampaignProductResponse (CampaignProduct campaignProduct){
        CampaignProductResponse campaignProductResponse = CampaignProductResponse.builder()
                .products(campaignProduct.getProduct())
                .campaigns(campaignProduct.getCampaign())
                .build();

        return campaignProductResponse;
    }
}
