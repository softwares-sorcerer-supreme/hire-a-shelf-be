package com.example.shelve.mapper;

import com.example.shelve.dto.request.BrandRequest;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Location;
import com.example.shelve.repository.LocationRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BrandMapper {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;

    public BrandResponse toBrandResponse(Brand brand) {
        if (brand == null)
            return null;

//        Set<LocationResponse> locationResponses = new HashSet<>();
//        brand.getLocations().forEach(location -> locationResponses.add(locationMapper.toLocationResponse(location)));

        return BrandResponse.builder()
                .id(brand.getId())
                .location(locationMapper.toLocationResponse(brand.getLocation()))
                .description(brand.getDescription())
                .logo(brand.getLogo())
                .name(brand.getName())
                .phone(brand.getPhone())
                .status(brand.isStatus())
                .build();
    }

    public Brand toBrand(BrandRequest brandRequest) {
        return Brand.builder()
//                .loc
                .build();
    }


}
