package com.example.shelve.controllers;

import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shelve")
public class ShelvesController {

    @Autowired
    private ShelvesService shelvesService;

    @GetMapping("/{id}")
    public ResponseEntity<ShelvesResponse> getShelve(@PathVariable Long id) {
        return new ResponseEntity<>(shelvesService.getShelve(id), HttpStatus.OK);
    }

}
