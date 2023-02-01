package com.example.shelve.controllers;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getAllCampaign() {
        return new ResponseEntity<>(campaignService.getAllCampaign(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponse> getCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(campaignService.getCampaign(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<CampaignResponse> createNewCampaign(@RequestBody CampaignRequest campaign) {
        return new ResponseEntity<>(campaignService.createNewCampaign(), HttpStatus.OK);
    }

}
