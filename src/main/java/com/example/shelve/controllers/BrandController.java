package com.example.shelve.controllers;

import com.example.shelve.dto.request.BrandRequest;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Brand;
import com.example.shelve.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrand() {
        return new ResponseEntity<>(brandService.getAllBrand(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getCampaign(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.getBrand(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateCampaign(@PathVariable Long id,
                                                        @Valid @RequestBody BrandRequest brandRequest) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandResponse> deleteBrand(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.deleteBrand(id), HttpStatus.OK);
    }
}
