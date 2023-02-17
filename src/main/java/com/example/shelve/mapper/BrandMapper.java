package com.example.shelve.mapper;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.entities.Brand;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class BrandMapper {

    public BrandResponse toBrandResponse(Brand brand) {
        if (brand == null)
            return null;

        return BrandResponse.builder()
                .id(brand.getId())
                .address(brand.getAddress())
                .description(brand.getDescription())
                .logo(brand.getLogo())
                .name(brand.getName())
                .phone(brand.getPhone())
                .EStatus(brand.getEStatus().getName())
                .build();
    }
}
