package com.example.shelve.mapper;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CampaignMapper {

    @Autowired
    private BrandMapper brandMapper;

    public CampaignResponse toCampaignResponse(Campaign campaign) {
        CampaignResponse campaignResponse = CampaignResponse.builder()
                .id(campaign.getId())
                .content(campaign.getContent())
                .duration(campaign.getDuration())
                .createdDate(campaign.getCreatedDate())
                .startDate(campaign.getStartDate())
                .duration(campaign.getDuration())
                .expirationDate(campaign.getExpirationDate())
                .imgURL(campaign.getImgURL())
                .title(campaign.getTitle())
                .brand(brandMapper.toBrandResponse(campaign.getBrand()))
//                .status(campaign.getStatus().getName())
                .build();

        return campaignResponse;
    }

    public Campaign toCampaign(CampaignRequest campaignRequest) {
        Campaign campaign = Campaign.builder()
                .title(campaignRequest.getTitle())
                .content(campaignRequest.getContent())
                .startDate(campaignRequest.getStartDate())
                .expirationDate(campaignRequest.getExpirationDate())
                .duration(campaignRequest.getDuration())
                .brand(campaignRequest.getBrand())
                .EStatus(campaignRequest.getEStatus())
                .build();

        return campaign;
    }
}
