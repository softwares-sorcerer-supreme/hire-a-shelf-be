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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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


        List<ShelvesType> listShelvesType = new ArrayList<>();
        //get list shelve type
        campaignRequest.getShelveTypes().forEach(i -> listShelvesType.add(
                shelvesTypeRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Shelve Type ID: " + i + " not existed!"))
        ));

        //add to Campaign Shelve Type table
        listShelvesType.forEach(shelvesType ->
                camapignShelvesTypeRepository.save(new CampaignShelveType().builder()
                        .campaign(campaignSaved)
                        .shelvesType(shelvesType)
                        .status(true)
                        .build())
        );

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



}
