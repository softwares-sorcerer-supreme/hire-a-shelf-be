package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ImageResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ImageMapper;
import com.example.shelve.repository.ImageRepository;
import com.example.shelve.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper mapper;
    @Override
    public List<ImageResponse> getAllImage() {
        List<ImageResponse> imageResponses = new ArrayList<>();
        imageRepository.findAll().forEach(x -> imageResponses.add(mapper.toImageResponse(x)));
        return imageResponses;
    }

    @Override
    public ImageResponse getImage(Long id) {
        ImageResponse imageResponse = mapper.toImageResponse(imageRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found!")));
        ;
        return imageResponse;
    }
}
