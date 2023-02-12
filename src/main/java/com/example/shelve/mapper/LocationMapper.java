package com.example.shelve.mapper;


import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.entities.Location;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class LocationMapper {

    public LocationResponse toLocationResponse (Location location){
        LocationResponse locationResponse = LocationResponse.builder()
                .id(location.getId())
                .address(location.getAddress())
                .city(location.getCity())
                .district(location.getDistrict())
                .build();
        return locationResponse;
    }
}
