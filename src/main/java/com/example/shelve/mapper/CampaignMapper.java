package com.example.shelve.mapper;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignMapper {
    public List<CampaignResponse> toListCampaignResponse(List<Campaign> campaigns) {
        return null;
    }
}
