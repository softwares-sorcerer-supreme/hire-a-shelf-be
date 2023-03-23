package com.example.shelve.mapper;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.*;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.CampaignProduct;
import com.example.shelve.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CampaignMapper {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ShelvesTypeMapper shelvesTypeMapper;

    public CampaignResponse toCampaignResponse(Campaign campaign) {
        Set<ProductResponse> productResponseSet = new HashSet<>();
        List<StoreResponse> appliers = new ArrayList<>();
        if (campaign.getCampaignProducts() != null) {
            campaign.getCampaignProducts().forEach(campaignProduct ->
                    productResponseSet.add(productMapper.toProductResponse(campaignProduct.getProduct()))
            );
        }
        if (campaign.getContracts() != null) {
            campaign.getContracts().forEach(contract ->
                    appliers.add(storeMapper.toStoreResponse(contract.getStore()))
            );
        }

        Set<ShelvesTypeResponse> shelvesTypeResponses = new HashSet<>();
        if(campaign.getCampaignShelvesTypes() != null){
            campaign.getCampaignShelvesTypes().forEach(campaignShelvesType -> {
                shelvesTypeResponses.add(shelvesTypeMapper.toShelvesTypeResponse(campaignShelvesType.getShelvesType()));
            });
        }

        return CampaignResponse.builder()
                .id(campaign.getId())
                .content(campaign.getContent())
                .duration(campaign.getDuration())
                .createdDate(campaign.getCreatedDate())
                .startDate(campaign.getStartDate())
                .duration(campaign.getDuration())
                .expirationDate(campaign.getExpirationDate())
                .productResponseSet(productResponseSet)
                .appliers(appliers)
                .imgURL(campaign.getImgURL())
                .title(campaign.getTitle())
                .city(campaign.getCity())
                .shelvesTypeResponses(shelvesTypeResponses)
                .brand(brandMapper.toBrandResponse(campaign.getBrand()))
                .status(campaign.getEStatus().getName())
                .build();
    }

    public Campaign toCampaign(CampaignRequest campaignRequest) {

        return Campaign.builder()
                .title(campaignRequest.getTitle())
                .content(campaignRequest.getContent())
                .startDate(campaignRequest.getStartDate())
                .expirationDate(campaignRequest.getExpirationDate())
                .duration(campaignRequest.getDuration())
                .city(campaignRequest.getCity())
                .EStatus(campaignRequest.getEStatus())
                .build();
    }
}
