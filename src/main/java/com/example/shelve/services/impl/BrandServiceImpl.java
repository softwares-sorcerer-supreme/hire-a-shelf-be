package com.example.shelve.services.impl;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.BrandMapper;
import com.example.shelve.repository.BrandRepository;
import com.example.shelve.services.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper mapper;

    @Override
    @Cacheable(value = "brand")
    public List<BrandResponse> getAllBrand() {
        log.error("Get brand", "get all brabd");
        List<BrandResponse> brandResponses = new ArrayList<>();

        brandRepository.findAll().forEach(x -> brandResponses.add(mapper.toBrandResponse(x)));
        return brandResponses;
    }

    @Override
    @Cacheable(value = "brand", key = "#id")
    public BrandResponse getBrand(Long id) {
        BrandResponse brandResponse = mapper.toBrandResponse(brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!")));
        ;
        return brandResponse;
    }
}
