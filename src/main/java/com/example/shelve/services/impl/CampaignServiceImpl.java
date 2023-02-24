package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CampaignMapper;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.services.CampaignService;
import com.example.shelve.services.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private StorageService storageService;
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper mapper;

    public List<CampaignResponse> getAllCampaign() {
        List<CampaignResponse> campaignResponses = new ArrayList<>();
        campaignRepository.findAll().forEach(x -> campaignResponses.add(mapper.toCampaignResponse(x)));
        return campaignResponses;
    }

    @Override
    public CampaignResponse getCampaign(Long id) {
        CampaignResponse campaignResponse = mapper.toCampaignResponse(campaignRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!")));

        return campaignResponse;
    }

    @Override
    public CampaignResponse approveCampaign(Long id, EStatus status) {
        return null;
    }

    @Override
    public CampaignResponse updateCampaign(Long id, CampaignRequest campaign) {
        return null;
    }

    @Override
    public CampaignResponse disableCampaign(Long id) {
        return null;
    }

    @Override
    public CampaignResponse createNewCampaign(String campaignStr, MultipartFile file) {
        String ImageLinkCloud = storageService.uploadFile(file);
        Campaign savedCampaign = new Campaign();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            savedCampaign = objectMapper.readValue(campaignStr, Campaign.class);
        } catch (IOException e){
            System.out.printf("Error", e.toString());
        }
        savedCampaign.setImgURL(ImageLinkCloud);
        return mapper.toCampaignResponse(campaignRepository.save(savedCampaign));
    }

}
