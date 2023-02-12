package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Brand;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class BrandMapper {

    public BrandResponse toBrandResponse (Brand brand){
        BrandResponse brandResponse = BrandResponse.builder()
                .id(brand.getId())
                .address(brand.getAddress())
                .description(brand.getDescription())
                .email(brand.getEmail())
                .logo(brand.getLogo())
                .name(brand.getName())
                .phone(brand.getPhone())
                .status(brand.isStatus())
                .build();

        return brandResponse;
    }
}
