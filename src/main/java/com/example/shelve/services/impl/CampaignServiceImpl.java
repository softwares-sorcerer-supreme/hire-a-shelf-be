package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CampaignMapper;
import com.example.shelve.repository.*;
import com.example.shelve.services.CampaignService;
import com.example.shelve.services.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private CampaignProductRepository campaignProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CampaignShelvesTypeRepository camapignShelvesTypeRepository;
    @Autowired
    private ShelvesTypeRepository shelvesTypeRepository;
    @Autowired
    private StorageService storageService;


    @Autowired
    private CampaignMapper campaignMapper;


    @Override
    @Cacheable(value = "campaign")
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
        Campaign campaign = campaignMapper.toCampaign(campaignRequest);
        campaign.setCreatedDate(new Date(System.currentTimeMillis()));

        if (campaign.getStartDate().before(campaign.getCreatedDate()))
            throw new BadRequestException("Start Date must be after Today!");

        if (campaign.getExpirationDate().before(campaign.getStartDate()))
            throw new BadRequestException("Expiration Date must be after Start Date");

        campaign.setEStatus(EStatus.PENDING);
        Campaign campaignSaved = campaignRepository.save(campaign);

        List<Product> listProduct = new ArrayList<>();
        //get list object product and check
        campaignRequest.getProducts().forEach(i -> listProduct.add(
                productRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product ID: " + i + " not existed!"))
        ));

        //add to CampaignProduct table
        listProduct.forEach(product -> {
            campaignProductRepository.save(new CampaignProduct().builder()
                    .status(true)
                    .product(product)
                    .campaign(campaignSaved)
                    .build());
        });


        List<ShelveType> listShelvesType = new ArrayList<>();
        //get list shelve type
        campaignRequest.getShelveTypes().forEach(i -> listShelvesType.add(
                shelvesTypeRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Shelve Type ID: " + i + " not existed!"))
        ));

        //add to Campaign Shelve Type table
        listShelvesType.forEach(shelveType ->
                camapignShelvesTypeRepository.save(new CampaignShelveType().builder()
                        .campaign(campaignSaved)
                        .shelvesType(shelveType)
                        .status(true)
                        .build())
        );

        campaign.setImgURL(storageService.uploadFile(campaignRequest.getImgMultipart()));

        CampaignResponse campaignResponse = campaignMapper.toCampaignResponse(campaign);


        return campaignResponse;
    }

    @Override
    public CampaignResponse approveCampaign(Long id, EStatus status) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        campaign.setEStatus(status);
        Campaign campaignSaved = campaignRepository.save(campaign);

        return campaignMapper.toCampaignResponse(campaignSaved);

        }

    @Override
    public CampaignResponse updateCampaign(Long id, CampaignRequest campaignRequest) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        if(campaign.getEStatus() != EStatus.PENDING)
            throw new BadRequestException("Can't update campaign after approve!");

        campaign.setTitle(campaignRequest.getTitle());
        campaign.setContent(campaignRequest.getContent());
        campaign.setStartDate(campaignRequest.getStartDate());
        campaign.setExpirationDate(campaignRequest.getExpirationDate());
        campaign.setDuration(campaignRequest.getDuration());
        campaign.setImgURL(storageService.uploadFile(campaignRequest.getImgMultipart()));

        Campaign campaignSaved = campaignRepository.save(campaign);

        return campaignMapper.toCampaignResponse(campaignSaved);
    }

    @Override
    public CampaignResponse disableCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        campaign.setEStatus(EStatus.DISABLE);
        Campaign campaignSaved = campaignRepository.save(campaign);

        return campaignMapper.toCampaignResponse(campaignSaved);
    }

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
        return campaignMapper.toCampaignResponse(campaignRepository.save(savedCampaign));
    }

}
