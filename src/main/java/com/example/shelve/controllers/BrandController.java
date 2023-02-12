package com.example.shelve.controllers;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllCampaign() {
        return new ResponseEntity<>(brandService.getAllBrand(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.getBrand(id), HttpStatus.FOUND);
    }
}
