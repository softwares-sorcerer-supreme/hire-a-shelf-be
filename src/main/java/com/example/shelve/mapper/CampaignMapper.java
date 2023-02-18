package com.example.shelve.mapper;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CampaignMapper {

    public CampaignResponse toCampaignResponse (Campaign campaign){
        CampaignResponse campaignResponse = CampaignResponse.builder()
                .id(campaign.getId())
                .content(campaign.getContent())
                .duration(campaign.getDuration())
                .createdDate(campaign.getCreatedDate())
                .startDate(campaign.getStartDate())
                .expirationDate(campaign.getExpirationDate())
                .imgURL(campaign.getImgURL())
                .title(campaign.getTitle())
                .brand(campaign.getBrand())
                .shelves(campaign.getShelves())
                .build();

        return campaignResponse;
    }

    public CampaignResponse toCampaignResponse (CampaignRequest campaignRequest){
        CampaignResponse campaignResponse = CampaignResponse.builder()
                .content(campaignRequest.getContent())
                .duration(campaignRequest.getDuration())
                .createdDate(campaignRequest.getCreatedDate())
                .startDate(campaignRequest.getStartDate())
                .expirationDate(campaignRequest.getExpirationDate())
                .title(campaignRequest.getTitle())
                .build();

        return campaignResponse;
    }

    public Campaign toCampaign (CampaignRequest campaignRequest){
            Campaign campaign = Campaign.builder()
                .content(campaignRequest.getContent())
                .duration(campaignRequest.getDuration())
                .createdDate(campaignRequest.getCreatedDate())
                .startDate(campaignRequest.getStartDate())
                .expirationDate(campaignRequest.getExpirationDate())
                .title(campaignRequest.getTitle())
                .build();

        return campaign;
    }
}
