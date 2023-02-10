package com.example.shelve.services.impl;

import com.example.shelve.repository.LocationRepository;
import com.example.shelve.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
}
