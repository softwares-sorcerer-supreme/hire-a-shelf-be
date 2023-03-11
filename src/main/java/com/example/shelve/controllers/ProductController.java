package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.Product;
import com.example.shelve.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public APIResponse<List<ProductResponse>> getAllProductWithFilter(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                 @RequestParam(required = false, defaultValue = "0") long brandId,
                                                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false) List<Long> categoriesId) {
        return productService.getAllProductWithFilter(brandId, page, keyword, categoriesId);
    }
    @GetMapping("/brand/{brandId}")
    public List<ProductResponse> getAllBrandsAvailableProductWithoutFilter(@PathVariable long brandId) {
        return productService.getAllBrandsAvailableProductWithoutFilter(brandId);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductResponse> createProduct(@ModelAttribute @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(productService.creteProduct(productRequest), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId,
                                                         @RequestBody @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(productId, productRequest), HttpStatus.OK);
    }

}
