package com.example.shelve.controllers;

import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAllLocation() {
        return new ResponseEntity<>(locationService.getAllLocation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getLocation(@PathVariable Long id) {
        return new ResponseEntity<>(locationService.getLocation(id), HttpStatus.OK);
    }
}
