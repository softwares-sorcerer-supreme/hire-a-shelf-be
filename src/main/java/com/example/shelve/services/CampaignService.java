package com.example.shelve.services;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.List;

public interface CampaignService {

    List<CampaignResponse> getAllCampaign();

    CampaignResponse getCampaign(Long id);

//    CampaignResponse createNewCampaign(String campaignStr, MultipartFile file);

    CampaignResponse createNewCampaign(CampaignRequest campaignRequest);

    CampaignResponse approveCampaign(Long id, EStatus status) throws FirebaseMessagingException;

    CampaignResponse updateCampaign(Long id, CampaignRequest campaign);

    CampaignResponse disableCampaign(Long id);

    APIResponse<List<CampaignResponse>> getAllCampaignsWithFilter(Long brandId, String keyword, int page, List<String> statusList);

    APIResponse<List<CampaignResponse>> getListCampaignsWithFilterForHomePage(Long storeId, String keyword, int page, List<String> states, String suggestByCategory, String filterByLocation);
}
