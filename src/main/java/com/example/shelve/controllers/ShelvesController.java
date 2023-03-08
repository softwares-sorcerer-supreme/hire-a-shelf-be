package com.example.shelve.controllers;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shelve")
public class ShelvesController {

    @Autowired
    private ShelvesService shelvesService;

    @GetMapping("/{id}")
    public ResponseEntity<ShelvesResponse> getShelve(@PathVariable Long id) {
        return new ResponseEntity<>(shelvesService.getShelve(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShelvesResponse> createShelve(@RequestBody ShelvesRequest shelvesRequest) {
        return new ResponseEntity<>(shelvesService.createShelve(shelvesRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShelvesResponse> updateShelve(@PathVariable Long id, @Valid @RequestBody ShelvesRequest shelvesRequest) {
        return new ResponseEntity<>(shelvesService.updateShelve(id, shelvesRequest), HttpStatus.OK);
    }

}
