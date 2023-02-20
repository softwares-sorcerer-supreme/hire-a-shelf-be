package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.CampaignService;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(campaignService.getCampaign(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<CampaignResponse> createNewCampaign(@RequestBody CampaignRequest campaign) {
        return new ResponseEntity<>(campaignService.createNewCampaign(campaign), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignResponse> approveCampaign(@RequestParam EStatus status,
                                                            @PathVariable Long id
    ) {
        return new ResponseEntity<>(campaignService.approveCampaign(id, status), HttpStatus.OK);
    }

}
