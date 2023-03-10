package com.example.shelve.services;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;

import java.util.List;

public interface CampaignService {

    List<CampaignResponse> getAllCampaign();

    CampaignResponse getCampaign(Long id);

//    CampaignResponse createNewCampaign(String campaignStr, MultipartFile file);

    CampaignResponse createNewCampaign(CampaignRequest campaignRequest);

    CampaignResponse approveCampaign(Long id, EStatus status);

    CampaignResponse updateCampaign(Long id, CampaignRequest campaign);

    CampaignResponse disableCampaign(Long id);

    APIResponse<List<CampaignResponse>> getAllCampaignsWithFilter(Long brandId, String keyword, int page, List<String> statusList);
}
