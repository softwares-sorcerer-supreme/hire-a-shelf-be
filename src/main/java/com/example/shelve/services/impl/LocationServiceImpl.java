package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.LocationMapper;
import com.example.shelve.repository.LocationRepository;
import com.example.shelve.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper mapper;

    @Override
    public List<LocationResponse> getAllLocation() {
        List<LocationResponse> locationResponses = new ArrayList<>();
        locationRepository.findAll().forEach(x -> locationResponses.add(mapper.toLocationResponse(x)));
        return locationResponses;
    }

    @Override
    public LocationResponse getLocation(Long id) {
        LocationResponse locationResponse = mapper.toLocationResponse(locationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found!")));
        ;
        return locationResponse;
    }
}
