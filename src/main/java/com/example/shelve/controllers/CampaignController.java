package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<CampaignResponse> createNewCampaign(@RequestParam(value = "campaign") String campaign, @RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(campaignService.createNewCampaign(campaign, file), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignResponse> approveCampaign(@RequestParam EStatus status,
                                                            @PathVariable Long id
    ) {
        return new ResponseEntity<>(campaignService.approveCampaign(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CampaignResponse> disableCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(campaignService.disableCampaign(id), HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<CampaignResponse> updateCamapaign(@RequestBody CampaignRequest campaign,
//                                                            @PathVariable Long id) {
//        return new ResponseEntity<>(campaignService.updateCampaign(id, campaign), HttpStatus.OK);
//    }

}
