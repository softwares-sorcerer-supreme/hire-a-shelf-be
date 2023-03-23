package com.example.shelve.controllers;

import com.example.shelve.dto.request.CategoryRequest;
import com.example.shelve.dto.request.ShelvesTypeRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.CategoryResponse;
import com.example.shelve.dto.response.ShelvesTypeResponse;
import com.example.shelve.services.CampaignService;
import com.example.shelve.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CategoryResponse>> getCategoriesByStatus(@PathVariable boolean status) {
        return new ResponseEntity<>(categoryService.getCategoriesByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequest), HttpStatus.OK);
    }

}
