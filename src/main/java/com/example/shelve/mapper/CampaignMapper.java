package com.example.shelve.mapper;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;
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
    private BrandMapper brandMapper;

    public CampaignResponse toCampaignResponse(Campaign campaign) {
        System.out.println(campaign.getCampaignProducts());
//        List<Product> productList = new ArrayList<>();
//                campaign.getCampaignProducts().forEach(
//                campaignProduct ->
//                    productList.add(campaignProduct.getProduct())
//
//        );
//        Set<ProductResponse> productResponseList = new HashSet<>();
//        productList.forEach((x -> productResponseList.add(productMapper.toProductResponse(x))));
        return CampaignResponse.builder()
                .id(campaign.getId())
                .content(campaign.getContent())
                .duration(campaign.getDuration())
                .createdDate(campaign.getCreatedDate())
                .startDate(campaign.getStartDate())
//                .products(productResponseList)
                .duration(campaign.getDuration())
                .expirationDate(campaign.getExpirationDate())
                .imgURL(campaign.getImgURL())
                .title(campaign.getTitle())
                .city(campaign.getCity())
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
