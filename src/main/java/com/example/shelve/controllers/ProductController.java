package com.example.shelve.controllers;

import com.example.shelve.dto.request.ProductRequest;
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

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.creteProduct(productRequest), HttpStatus.OK);
    }

}
