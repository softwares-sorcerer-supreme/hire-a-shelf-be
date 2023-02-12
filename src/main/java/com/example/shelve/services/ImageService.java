package com.example.shelve.services;

import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ImageResponse;

import java.util.List;

public interface ImageService {

    List<ImageResponse> getAllImage();

    ImageResponse getImage(Long id);
}
