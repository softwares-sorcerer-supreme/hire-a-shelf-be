package com.example.shelve.controllers;

import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.Product;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
//    public ResponseEntity<List<ProductResponse>> getAllProduct() {
//        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
//    }

    @GetMapping
    public APIResponse<List<ProductResponse>> getCampaignByBrand(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                 @RequestParam(required = false, defaultValue = "0") long brandId,
                                                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false) List<Long> categoriesId) {
        return productService.getBrandProducts(brandId, keyword, page, categoriesId);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.creteProduct(productRequest), HttpStatus.OK);
    }

}
