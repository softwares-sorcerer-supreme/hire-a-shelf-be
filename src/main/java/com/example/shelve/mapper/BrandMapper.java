package com.example.shelve.mapper;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.LocationResponse;
import com.example.shelve.entities.Brand;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BrandMapper {

    @Autowired
    private LocationMapper locationMapper;

    public BrandResponse toBrandResponse(Brand brand) {
        if (brand == null)
            return null;

        Set<LocationResponse> locationResponses = new HashSet<>();
        brand.getLocations().forEach(location -> locationResponses.add(locationMapper.toLocationResponse(location)));

        return BrandResponse.builder()
                .id(brand.getId())
                .location(locationResponses)
                .description(brand.getDescription())
                .logo(brand.getLogo())
                .name(brand.getName())
                .phone(brand.getPhone())
                .status(brand.isStatus())
                .build();
    }
}
