package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.mapper.CampaignMapper;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    public List<CampaignResponse> getAllCampaign() {
        //Campaign
//        return campaignMapper.toListCampaignResponse(campaignRepository.findAll());;
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
