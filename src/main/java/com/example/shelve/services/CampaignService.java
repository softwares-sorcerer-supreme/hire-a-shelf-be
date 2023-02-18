package com.example.shelve.services;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CampaignService {

    List<CampaignResponse> getAllCampaign();

    CampaignResponse getCampaign(Long id);

    CampaignResponse createNewCampaign(String campaignStr, MultipartFile file);

}
