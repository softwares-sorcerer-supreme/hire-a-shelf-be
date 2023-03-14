package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.CampaignService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

//    @GetMapping
//    public ResponseEntity<List<CampaignResponse>> getAllCampaign() {
//        return new ResponseEntity<>(campaignService.getAllCampaign(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponse> getCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(campaignService.getCampaign(id), HttpStatus.OK);
    }

    @GetMapping
    public APIResponse<List<CampaignResponse>> getAllCampaignsWithFilter(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                         @RequestParam(required = false, defaultValue = "0") long brandId,
                                                                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                         @RequestParam(required = false, defaultValue = "") List<String> states) {
        return campaignService.getAllCampaignsWithFilter(brandId, keyword, page, states);
    }

    @GetMapping("/home")
    public APIResponse<List<CampaignResponse>> getAllCampaignsWithFilterForHomePage(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                                    @RequestParam(required = false, defaultValue = "0") long storeId,
                                                                                    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                    @RequestParam(required = false, defaultValue = "") List<String> states,
                                                                                    @RequestParam(required = false, defaultValue = "") String filterBy) {
        return campaignService.getListCampaignsWithFilterForHomePage(storeId, keyword, page, states, filterBy);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CampaignResponse> createNewCampaign(@ModelAttribute
                                                              @Valid CampaignRequest campaign) {
        return new ResponseEntity<>(campaignService.createNewCampaign(campaign), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignResponse> approveCampaign(@RequestParam EStatus status,
                                                            @PathVariable Long id
    ) throws FirebaseMessagingException {
        return new ResponseEntity<>(campaignService.approveCampaign(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CampaignResponse> disableCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(campaignService.disableCampaign(id), HttpStatus.OK);
    }

    @PutMapping("/u/{id}")
    public ResponseEntity<CampaignResponse> updateCampaign(@RequestBody CampaignRequest campaign,
                                                           @PathVariable Long id) {
        return new ResponseEntity<>(campaignService.updateCampaign(id, campaign), HttpStatus.OK);
    }

}
