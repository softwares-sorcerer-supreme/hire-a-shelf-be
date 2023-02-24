package com.example.shelve.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadFile(MultipartFile multipartFile);
}
