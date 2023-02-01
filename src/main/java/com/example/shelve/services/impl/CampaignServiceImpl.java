package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<CampaignResponse> getAllCampaign() {
        return null;
    }

    @Override
    public CampaignResponse getCampaign() {
        return null;
    }

    @Override
    public CampaignResponse createNewCampaign() {
        return null;
    }

}
