package com.example.shelve.services.impl;

import com.example.shelve.repository.ImageRepository;
import com.example.shelve.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
}
