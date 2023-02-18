package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<CampaignResponse> createNewCampaign(@RequestPart("campaign") String campaignStr, @RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(campaignService.createNewCampaign(campaignStr, file), HttpStatus.OK);
    }

}
