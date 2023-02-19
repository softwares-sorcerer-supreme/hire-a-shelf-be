package com.example.shelve.controllers;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.services.CampaignProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/campaign_product")
public class CampaignProductController {

    @Autowired
    private CampaignProductService campaignProductService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CampaignProductResponse>> getProductByCampaignId(@PathVariable  Long id) {
        return new ResponseEntity<>(campaignProductService.getProductsByCampaignId(id), HttpStatus.OK);
    }
}
