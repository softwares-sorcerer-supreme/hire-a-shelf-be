package com.example.shelve.services;


import com.example.shelve.dto.response.LocationResponse;

import java.util.List;

public interface LocationService {

    List<LocationResponse> getAllLocation();

    LocationResponse getLocation(Long id);
}
