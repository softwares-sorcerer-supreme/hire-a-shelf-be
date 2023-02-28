package com.example.shelve.services;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CampaignService {

    List<CampaignResponse> getAllCampaign();

    CampaignResponse getCampaign(Long id);

    CampaignResponse createNewCampaign(String campaignStr, MultipartFile file);

    CampaignResponse approveCampaign(Long id, EStatus status);

    CampaignResponse updateCampaign(Long id, CampaignRequest campaign);

    CampaignResponse disableCampaign(Long id);
}
