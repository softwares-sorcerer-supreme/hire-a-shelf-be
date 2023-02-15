package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CampaignMapper;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    public List<CampaignResponse> getAllCampaign() {
        List<CampaignResponse> campaignResponses = new ArrayList<>();
        campaignRepository.findAll().forEach(x -> campaignResponses.add(campaignMapper.toCampaignResponse(x)));
        return campaignResponses;
    }

    @Override
    public CampaignResponse getCampaign(Long id) {
        CampaignResponse campaignResponse = campaignMapper.toCampaignResponse(campaignRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!")));

        return campaignResponse;
    }

    @Override
    public CampaignResponse createNewCampaign(CampaignRequest campaignRequest) {
        campaignRepository.save(campaignMapper.toCampaign(campaignRequest));
        CampaignResponse campaignResponse = campaignMapper.toCampaignResponse(campaignRequest);



        return campaignResponse;
    }

}
