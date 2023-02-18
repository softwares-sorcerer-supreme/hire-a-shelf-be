package com.example.shelve.controllers;

import com.example.shelve.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file")MultipartFile file){
        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
    }
}
