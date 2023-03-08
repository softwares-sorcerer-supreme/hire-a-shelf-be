package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
        return new ResponseEntity<>(campaignService.getCampaign(id), HttpStatus.OK);
    }

    @GetMapping("/brand")
    public APIResponse<List<CampaignResponse>> getCampaignByBrand(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                  @RequestParam long brandId, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return campaignService.getBrandCampaigns(brandId, keyword, page);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CampaignResponse> createNewCampaign(@ModelAttribute
                                                              @Valid CampaignRequest campaign) {
        return new ResponseEntity<>(campaignService.createNewCampaign(campaign), HttpStatus.OK);
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
