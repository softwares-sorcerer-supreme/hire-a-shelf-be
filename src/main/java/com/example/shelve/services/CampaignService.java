package com.example.shelve.services;

import com.example.shelve.dto.response.CampaignResponse;

import java.util.List;

public interface CampaignService {

    List<CampaignResponse> getAllCampaign();

    CampaignResponse getCampaign();

    CampaignResponse createNewCampaign();
}
