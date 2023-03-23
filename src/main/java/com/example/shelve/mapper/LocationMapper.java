package com.example.shelve.mapper;


import com.example.shelve.dto.request.LocationRequest;
import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.entities.Location;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationResponse toLocationResponse (Location location){
        if(location == null)
            return null;
        return LocationResponse.builder()
                .id(location.getId())
                .address(location.getAddress())
                .city(location.getCity())
                .district(location.getDistrict())
                .ward(location.getWard())
                .build();
    }

    public Location toLocation(LocationRequest locationRequest) {
        if(locationRequest == null)
            return new Location();

        return Location.builder()
                .district(locationRequest.getDistrict())
                .city(locationRequest.getCity())
                .address(locationRequest.getAddress())
                .status(locationRequest.isStatus())
                .ward(locationRequest.getWard())
                .build();
    }
}
