package com.example.shelve.controllers;

import com.example.shelve.dto.response.StoreCategoryResponse;
import com.example.shelve.dto.response.StoreResponse;
import com.example.shelve.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable Long id) {
        return new ResponseEntity<>(storeService.getStore(id), HttpStatus.OK);
    }
    @PostMapping("/category")
    public ResponseEntity<List<StoreCategoryResponse>> addFavouriteCategory(@RequestParam List<Long> categoriesId, @RequestParam Long storeId) {
        return new ResponseEntity<>(storeService.addFavouriteCategory(categoriesId, storeId), HttpStatus.OK);
    }

}
