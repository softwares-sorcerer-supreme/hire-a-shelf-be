package com.example.shelve.services.impl;

import com.example.shelve.dto.request.BrandRequest;
import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Location;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.BrandMapper;
import com.example.shelve.mapper.LocationMapper;
import com.example.shelve.repository.BrandRepository;
import com.example.shelve.repository.LocationRepository;
import com.example.shelve.services.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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
    private BrandMapper brandMapper;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationMapper locationMapper;

    @Override
    @Cacheable(value = "brand")
    public List<BrandResponse> getAllBrand() {
        log.error("Get brand", "get all brabd");
        List<BrandResponse> brandResponses = new ArrayList<>();

        brandRepository.findAll().forEach(x -> brandResponses.add(brandMapper.toBrandResponse(x)));
        return brandResponses;
    }

    @Override
    @Cacheable(value = "brand", key = "#id")
    public BrandResponse getBrand(Long id) {
        BrandResponse brandResponse = brandMapper.toBrandResponse(brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!")));

        return brandResponse;
    }

    @Override
    @CachePut(value = "brand", key = "#id")
    public BrandResponse updateBrand(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        brand.setLogo(brandRequest.getLogo());
        brand.setName(brandRequest.getName());
        brand.setDescription(brandRequest.getDescription());

        Location location = locationRepository.save(locationMapper.toLocation(brandRequest.getLocation()));
        brand.setLocation(location);

        Brand brandSaved = brandRepository.save(brand);

        return brandMapper.toBrandResponse(brandSaved);
    }

    @Override
    @CachePut(value = "brand", key = "#id")
    public BrandResponse deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        brand.setStatus(false);
        Brand brandSaved = brandRepository.save(brand);

        return brandMapper.toBrandResponse(brandSaved);
    }


}
